$(document).ready(
function() {

    $('[name="quantity_product"]').on('focus', function() {
        clear_error("#message_line_product");
        $('#answer1_product').html("");
        $('#error1_product').html("");
    });
 
 
    
    function disable_add_cart(flag) {
        $('[name="add_cart"]').prop('disabled', flag);
        $('[name="quantity_product"]').prop('disabled', flag);
    }
    
    $('[name="add_cart"]').on('click', function(e) {
        e.preventDefault();
        clear_error("#message_line_product");
        $('#answer1_product').html("");
        $('#error1_product').html("");
        if (!validate_quantity()) {
            write_error("#message_line_product", "Sorry, invalid product quanity."); 
        }
        else {
           send_form_data_cart();
        }
    });


  function write_error(id, msg) {
        $(id).html("<b>"+msg+"</b>");   
    }
    
    function clear_error(id) {
        $(id).html("");   
    }
 
    

    
    function validate_quantity() {
        var value = $('[name="quantity_product"]').val();
        value = $.trim(value);
        var pattern = /^[1-9]{1}[0-9]*$/;
        if(pattern.test(value))
            return true;
        return false;
    }
    

    
    function send_form_data_cart() {                 
        var url = "/jadrn026/servlet/proj3/AddCart";
        var sku = $('[name="sku_product"]').val();
         var quantity = $('[name="quantity_product"]').val();
       
        var params = "sku=" + sku + "&quantity=" + quantity + "&add=1";
        $.post(url, params, handleDataCart);
    }

   
    function handleDataCart(response) {    
        var tmpStr = response.split("||");
        var quantity=parseInt(tmpStr[0]);
        var inStock = parseInt(tmpStr[1]);
        if(inStock == 1) {
            $('#answer1_product').html("Item is added to your cart."); 
        } else {
            if ( quantity == 0) {
                $('#error1_product').html("Sorry, no items in stock"); 
            }
            else {
                $('#error1_product').html("Sorry, only" + " " + quantity + " "  + "items in stock");
            }
        }
    }

});
 
 


   
     
    

    
    

         
     
    
    
    
    
    
    
    
    
    
    


