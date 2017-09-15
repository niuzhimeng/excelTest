<%@ page contentType="text/html; charset=gb2312" %>
<!DOCTYPE html>
<html>
<head>
    <title>°Ù¶ÈµØÍ¼javascript</title>
    <script type="text/javascript">
        function initialize() {
            var mp = new BMap.Map('map');
            mp.centerAndZoom(new BMap.Point(121.2134, 31.0576), 15);
        }

        function loadScript() {
            var script = document.createElement("script");
            script.src = "http://api.map.baidu.com/api?v=1.4&callback=initialize";
            document.body.appendChild(script);
        }

        window.onload = loadScript;
    </script>
</head>
<body>
<div id="map" style="width: 1500px; height: 1000px"></div>
</body>
</html>