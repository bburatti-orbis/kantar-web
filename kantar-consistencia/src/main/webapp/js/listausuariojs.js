
$(function() {
	var url=ruta+"procedimiento";
	$.getJSON(url).done(function(dat) {
		window.headertab = dat;
		
	});
	
});