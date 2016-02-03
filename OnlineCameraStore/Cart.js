$(document).ready(
function() {

    $('[name="quantity_product"]').on('focus', function() {
        clear_error("#message_line_cart");
        $('#answer1_cart').html("");
        $('#error1_cart').html("");
    });
 
 
    
    function disable_add_cart(flag) {
        $('[name="add_cart"]').prop('disabled', flag);
        $('[name="quantity_product"]').prop('disabled', flag);
    }
    
    function validate_quantity(quantity ) {
        var pattern = /^[0-9]*$/;
        if(pattern.test(quantity))
            return true;
        return false;
    }
    
    $('.update_cart').on('click', function(e){
        e.preventDefault();
        
        var value = $(this).parent().find(".quantity_cart").val();
        var sku=$(this).attr('id').replace("update_cart", "");
        var quantity = $.trim(value);
       clear_error("#message_line_cart"+sku);
        $('#answer1_cart'+sku).html("");
        $('#error1_cart'+sku).html("");
        if (!validate_quantity( quantity )) {
            write_error("#message_line_cart", "Sorry, invalid product quanity."); 
        }
        else {
            if (quantity == "0") {
                remove_cart(sku);
            } else {
               send_form_data_cart(sku, quantity);
            }
        }
    });

  $('.remove_cart').on('click', function(e){
        e.preventDefault();
        var sku=$(this).attr('id').replace("remove_cart", "");
        remove_cart(sku);
    });

     
    function remove_cart(sku)   {
        window.location.href = "/jadrn026/servlet/proj3/ShowCart?skuRemove=" + sku;
    }

  function write_error(id, msg) {
        $(id).html("<b>"+msg+"</b>");   
    }
    
    function clear_error(id) {
        $(id).html("");   
    }
 
    
 $('[name="shopping"]').on('click', function(e) {
        e.preventDefault();
        window.location.href = "/jadrn026/proj3.html";
    });

    
 $('[name="checkout"]').on('click', function(e) {
        e.preventDefault();
        window.location.href = "/jadrn026/servlets/proj3/Checkout.html";
    });

 $('[name="order_cancel"]').on('click', function(e) {
        e.preventDefault();
        window.location.href = "/jadrn026/proj3.html";
    });
    
 $('[name="order_submit"]').on('click', function(e) {
        e.preventDefault();
        var cartSummary = $('[name="CartSummary"]').val();
        var cartCount = $('[name="CartCount"]').val();
        window.location.href = "/jadrn026/servlet/proj3/OrderSubmit?CartCount=" +
            cartCount + "&" + "CartSummary=" + cartSummary;
    });

    
    function send_form_data_cart(sku, quantity) {
        var form_data = "sku=" + sku + "&quantity=" + quantity + "&add=0";
            $.ajax( {
               type: 'POST',
               url: "/jadrn026/servlet/proj3/AddCart",
               data: form_data,
               success: function(response) {
                  handleDataCart(response, sku);
                   }
            });
    }

   
    function handleDataCart(response, sku) {    
        var tmpStr = response.split("||");
        var quantity=parseInt(tmpStr[0]);
        var inStock = parseInt(tmpStr[1]);
        var inStock = parseInt(tmpStr[1]);
        if(inStock == 1) {
            window.location.href = "/jadrn026/servlet/proj3/ShowCart";
        } else {
            if ( quantity == 0) {
                $('#error1_cart'+sku).html("Sorry, no items in stock"); 
            }
            else {
                $('#error1_cart'+sku).html("Sorry, only" + " " + quantity + " "  + "items in stock");
            }
        }
  }

});
 
 


   
     
    

    
    

         
     
    
    
    
    
    
    
    
    
    
    


