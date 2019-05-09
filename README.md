# Filter
这是一个实现安卓筛选框的插件，下面是演示效果：
![https://github.com/zhujiang521/Filter/blob/master/view.gif]
大家可以通过以下方式引用：
添加Filter到你的项目

	      implementation 'com.github.zhujiang521:Filter:1.0.0'
          maven { url 'https://jitpack.io' }
         
下面是使用方法：
public class MainActivity extends AppCompatActivity {

    private TextView one;
    //筛选框控件
    private FlowPopWindow flowPopWindow;
    //筛选框数据
    private List<FiltrateBean> dictList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParam();
        initView();
    }

    private void initView() {
        one = findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new FlowPopWindow(MainActivity.this, dictList);
                flowPopWindow.showAsDropDown(one);
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
                            Toast.makeText(MainActivity.this, "111"+sb.toString(), Toast.LENGTH_LONG).show();
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
        for (String sex : sexs) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(sex);
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);

        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("颜色");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (String color : colors) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(color);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);

        FiltrateBean fb3 = new FiltrateBean();
        fb3.setTypeName("企业");
        List<FiltrateBean.Children> childrenList3 = new ArrayList<>();
        for (String s : company) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(s);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);

        dictList.add(fb1);
        dictList.add(fb2);
        dictList.add(fb3);
    }
}
