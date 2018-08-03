package zj.it.bhne.filter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvFlow;

    private FlowPopWindow flowPopWindow;
    private List<FiltrateBean> dictList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParam();
        initView();
    }

    private void initView() {
        tvFlow = findViewById(R.id.tv_flow);
        tvFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new FlowPopWindow(MainActivity.this, dictList);
                flowPopWindow.showAsDropDown(tvFlow);
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        StringBuilder sb = new StringBuilder();
                        for (FiltrateBean fb : dictList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    sb.append(fb.getTypeName() + ":" + children.getValue() + "；");
                            }
                        }
                        if (!TextUtils.isEmpty(sb.toString()))
                            Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }


    //这些是假数据，真实项目中直接接口获取添加进来，FiltrateBean对象可根据自己需求更改
    private void initParam() {
        String[] sexs = {"男", "女"};
        String[] colors = {"红色", "浅黄色", "橙子色", "鲜绿色", "青色", "天蓝色", "紫色", "黑曜石色", "白色", "五颜六色"};
        String[] company = {"阿里巴巴集团", "腾讯集团", "华为技术服务有限公司", "小米", "www.xiaomi.com"};

        FiltrateBean fb1 = new FiltrateBean();
        fb1.setTypeName("性别");
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        for (int x = 0; x < sexs.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(sexs[x]);
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);

        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("颜色");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(colors[x]);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);

        FiltrateBean fb3 = new FiltrateBean();
        fb3.setTypeName("企业");
        List<FiltrateBean.Children> childrenList3 = new ArrayList<>();
        for (int x = 0; x < company.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(company[x]);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);

        dictList.add(fb1);
        dictList.add(fb2);
        dictList.add(fb3);
    }
}
