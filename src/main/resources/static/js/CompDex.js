// '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
$(window).on("load resize ", function() {
  var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
  $('.tbl-header').css({'padding-right':scrollWidth});
}).resize();

function CallFunction()
{
	var checkedValue = []; 
	var inputElements = document.getElementsByClassName('Checkedbox');
	for(i=0; i< inputElements.length; i++){
	      if(inputElements[i].checked){
	           		checkedValue.push(inputElements[i].value);
	      		}
		}
 
}