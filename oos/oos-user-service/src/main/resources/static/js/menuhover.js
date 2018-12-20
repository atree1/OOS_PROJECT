

	$("#searchOptionSelect").click(function(){
		if($(".layoutSelectBox").hasClass("open")){
			slideUp();
		}else{
			slideDown();
		}
	});    
	
	function slideUp(){
		$(".MultipleSelectBox").slideUp();
		$(".layoutSelectBox").removeClass("open");
		$("#searchOptionSelect").addClass("open");
	}
	
	function slideDown(){
		$(".MultipleSelectBox").slideDown();
		$(".layoutSelectBox").addClass("open");
		$("#searchOptionSelect").removeClass("open");
	}
	
$(".categoryMenu").hover(
        function () {
            $(this).show();
        },
        function () {
            $(this).hide();
        }
    );
	
/*$("#buildingSinsang").hover(
        function () {
        	$('.buildingMenu').show();
        }
  );*/

/*    function categoryMenuChange(num){
        $(". categoryMenu").hide();
        $(".selectCategory").removeClass('active');

        if(num == 1){
        	$('.buildingMenu').show();
        }else if(num == 2){
            $(".manCategoryArea").show();
        }else if(num == 3){
            $(".childCategoryArea").show();
        }
        $(this).addClass('active');
    }


    
    function categoryChange(num){
        $(".selectCategoryArea").removeClass('active');
        if(num == 1){
            $(".womanCategoryArea").addClass('active');
        }else if(num == 2){
            $(".manCategoryArea").addClass('active');
        }else if(num == 3){
            $(".childCategoryArea").addClass('active');
        }

    }
*/
    function categoryMenuShow(num){
    	$(".categoryMenu").show();
        if(num == 1){
        	$('.Top_Menu').show();
        }else if(num == 2){
            $(".Top_Menu").show();
        }

    }