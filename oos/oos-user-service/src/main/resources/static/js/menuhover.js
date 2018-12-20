

$("#searchOptionSelect").click(function(){
	if($(".layoutSelectBox").hasClass("open")){
		slideUp();
	}else{
		slideDown();
	}
});    


$(".Top_Menu").hover(
        function () {
            $(this).show();
        },
        function () {
            $(this).hide();
        }
    );


function categoryMenuShow(num){

    if(num == 1){
        $(".Top_Menu").show();
    }else if(num == 2){
        $(".Bottom_Menu").show();
    }

}
  