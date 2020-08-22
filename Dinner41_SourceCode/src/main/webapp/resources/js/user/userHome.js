$(document).ready(function(){
	$('#search_button').click(function(){
		if($('#search_input').val().trim() == ''){
			location.href="gm/all-/all-/1/store";
		}
		else{
			location.href="gm/all-/"+$('#search_input').val()+"/1/store";
		}
	});
});
