


require.config({
	baseUrl :'res/js',
	shim: {
        //easyui-lang-zh_CN.js也依赖jquery
        //'zhCN': ['jquery'],
        'easyui': ['jquery'],
        "jquery.form" : ["jquery"]
    },
    paths: {
		tools : 'tools',
        jquery: 'jquery/jquery.min',
		easyui: 'easyui/easyui.extload',
		ztree : 'ztree/js/jquery.ztree.extload',
		layer : 'layer/layer',
		'jquery.form' : 'jquery/jquery.form',
		zrender : 'zrender/build/zrender',
		'zrender/shape/Circle'    :  'zrender/build/zrender',
		'zrender/shape/Sector'    :  'zrender/build/zrender',
		'zrender/tool/color'      :  'zrender/build/zrender',
		'zrender/shape/Rectangle' :  'zrender/build/zrender',
		'zrender/shape/Polyline'  :  'zrender/build/zrender',
		'zrender/shape/Image'     :  'zrender/build/zrender' 
    }
});

