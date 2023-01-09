
$(document).ready(function(){
    $.ajax({
    	url:"/username",
    	type:"GET",
    	success:function(data){
    		$(".user-name").html(data);
    	}
    });
	
});
$(".sidebar-dropdown > a").click(function() {
  $(".sidebar-submenu").slideUp(200);
  if (
    $(this)
      .parent()
      .hasClass("active")
  ) {
    $(".sidebar-dropdown").removeClass("active");
    $(this)
      .parent()
      .removeClass("active");
  } else {
    $(".sidebar-dropdown").removeClass("active");
    $(this)
      .next(".sidebar-submenu")
      .slideDown(200);
    $(this)
      .parent()
      .addClass("active");
  }
});

$("#close-sidebar").click(function() {
  $(".page-wrapper").removeClass("toggled");
  if($(this).width()<=770){
    $('.title').removeClass('hide');
}
});
$("#show-sidebar").click(function() {
  $(".page-wrapper").addClass("toggled");
  if($(this).width()<=770){
    $('.title').addClass('hide');
}
});
$(document).ready(function() {
  $("#ville").change(function() {
    var villeId = $("#ville").val();
    $.ajax({
		type: 'GET',
		url: '/owner/zones/'+ villeId,
		success: function(result){
			console.log(result);
			var zone = '<option selected>Choisir ville</option>';
			var keys = Object.keys(result);
			for(var i=0;i<keys.length ;i++){
				zone += '<option value='+ result[i].id + '>'+ result[i].nom +'</option>';
			
			}
			$("#zone").html(zone);
		}
	});
  });
});
