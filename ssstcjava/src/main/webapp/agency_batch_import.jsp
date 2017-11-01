<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代理商批量导入</title>

</head>
<body>
<form method="post" enctype="multipart/form-data" action="agency/agencyImport">
	<br/><br/><br/><br/><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<select id="industry">

	</select>
	<br/><br/><br/><br/><br/><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	请选择要导入的excel：<input type="file" name="uploadFile" />
	<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" >
</form>
<script type="text/javascript">
    var industryArr =[{"name":"装修装饰","id":1},{"name":"建材","id":2},{"name":"婚庆服务","id":3},{"name":"首饰手表","id":4},{"name":"社会保障服务","id":5},{"name":"汽车销售","id":6},{"name":"汽车服务","id":7},{"name":"美容美发","id":8},{"name":"化妆洗护","id":9},{"name":"房产","id":10},{"name":"经纪代理","id":11},{"name":"休闲娱乐","id":12},{"name":"旅游机票","id":13},{"name":"酒店","id":14},{"name":"餐饮","id":15},{"name":"糖酒","id":16},{"name":"数码电子","id":17},{"name":"家具","id":18},{"name":"家电","id":19},{"name":"培训行业","id":20},{"name":"服装服饰","id":21},{"name":"鞋帽箱包","id":22},{"name":"养生保健品","id":23},{"name":"生鲜","id":24},{"name":"水果","id":25},{"name":"蔬菜","id":26},{"name":"农副用品","id":27}];

</script>
</body>
</html>