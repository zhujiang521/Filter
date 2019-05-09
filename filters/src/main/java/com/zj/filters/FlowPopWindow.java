package com.zj.filters;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zheng on 2017/11/13.
 */

public class FlowPopWindow extends PopupWindow {

    private final Activity context;
    private final List<FiltrateBean> dictList;
    private CustomHeightListView mListView;
    private TextView tvReset, tvConfirm;
    private View nullView;
    private FlowPopListViewAdapter adapter;
    private OnConfirmClickListener onConfirmClickListener;

    public FlowPopWindow(Activity context, List<FiltrateBean> dictList ) {
        this.context = context;
        this.dictList=dictList;
        initPop();
    }


    private void initPop() {
        View popView = View.inflate(context, R.layout.flow_pop_listview, null);
        //设置view
        this.setContentView(popView);
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(-1);
        this.setHeight(-2);
        //设置PopupWindow的焦点
        this.setFocusable(true);
        //设置窗口以外的地方点击可关闭pop
        this.setOutsideTouchable(true);
        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(0x33000000));

        mListView = (CustomHeightListView) popView.findViewById(R.id.listview);
        tvReset = (TextView) popView.findViewById(R.id.tv_reset);
        tvConfirm = (TextView) popView.findViewById(R.id.tv_confirm);
        nullView = popView.findViewById(R.id.view_null);

        adapter = new FlowPopListViewAdapter(context, dictList);
        mListView.setAdapter(adapter);
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int x = 0; x < dictList.size(); x++) {
                    List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
                    for (int y=0;y<childrenBeen.size();y++){
                        if (childrenBeen.get(y).isSelected())
                            childrenBeen.get(y).setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //自定义监听第三步
                onConfirmClickListener.onConfirmClick();
                dismiss();
            }
        });
        nullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    //自定义监听第二步
    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener){
        this.onConfirmClickListener=onConfirmClickListener;
    }

    //自定义监听第一步
    public interface OnConfirmClickListener{
        void onConfirmClick();
    }

}
