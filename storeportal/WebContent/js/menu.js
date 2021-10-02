jQuery(document).ready(function(){
   
   jQuery(".hasSubMenu").hover( function(){
       
       jQuery(this).parent().find(".submenus").css("display","block")

   }, function(){
       
       jQuery(this).parent().find(".submenus").css("display","none")
   })

jQuery(".submenus").hover( function(){
    
    jQuery(this).parent().find(".hasSubMenu").addClass("on")

    jQuery(this).css("display","block")

},function(){

    jQuery(this).parent().find(".hasSubMenu").removeClass("on")
    jQuery(this).css("display","none")
})
    
});