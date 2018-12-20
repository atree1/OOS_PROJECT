

$("#searchOptionSelect").click(function(){
	if($(".layoutSelectBox").hasClass("open")){
		slideUp();
	}else{
		slideDown();
	}
});    


$(".bigCategory, .TopLi").click(function(){
	actionForm.html("");
	actionForm.attr("action","/user/list")
			.append("<input type='hidden' name='cate' value='"+$(this).data("cat")+"'>")
			.submit();
});


function categoryMenuShow(num){

    if(num == 1){
        $(".Top_Menu").show();
    }else if(num == 2){
        $(".Bottom_Menu").show();
    }else if(num == 3){
        $(".Shoes_Menu").show();
    }

}
  