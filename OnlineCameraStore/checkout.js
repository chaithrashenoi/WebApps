$(document).ready(
function() {

var state_short = [ "AL", "AK", "AZ", "AR", "CO", "CT", "DE", "DC", "FL",
      "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME",
      "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
      "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI",
      "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI",
      "WY" ];
 
  var toWrite = "<option value=\"-1\">CA</option>";
  for(i=0; i<state_short.length; i++) {
    
      toWrite += "<option value=" +  state_short[i] + ">" + state_short[i] +"</option>\n";
  }
  $('[name="Shipping_state_select"]').append(toWrite);
  $('[name="Billing_state_select"]').append(toWrite);
  
  $('[name="cardType"]').on('click', function(e) {
        e.preventDefault();
           $('[name="cardType"]').css('background-color','white');
  });

  $('[name="Month"]').on('click', function(e) {
        e.preventDefault();
        $('[name="Month"]').css('background-color','white');
  });

  $('[name="Year"]').on('click', function(e) {
        e.preventDefault();
           $('[name="Year"]').css('background-color','white');
  });

 $('[name="reviewCheckout"]').on('click', function(e) {
        e.preventDefault();
         if(!EmptyCheck("Billing_name", "Billing Name")) {
         $('[name="Billing_name"]').focus();
       }
      else if(!EmptyCheck("Billing_address1", "Billing Address1")) {
         $('[name="Billing_address1"]').focus();
       }
      else if(!EmptyCheck("Billing_address2", "Billing Address2")) {
         $('[name="Billing_address2"]').focus();
       }
      else if(!EmptyCheck("Billing_city", "Billing City")) {
         $('[name="Billing_city"]').focus();
         
       }
       else if(!ZipCheck("Billing_zip", "Billing Zip")) {
         $('[name="Billing_zip"]').focus();
      }
      else if(!PhoneNumCheck("Billing_Phone", "Biling Phone")) {
         $('[name="Billing_Phone"]').focus();
       }
       else  if(!EmptyCheck("Shipping_name", "Shipping Name")) {
         $('[name="Shipping_name"]').focus();
       }
      else if(!EmptyCheck("Shipping_address1", "Shipping Address1")) {
         $('[name="Shipping_address1"]').focus();
       }
      else if(!EmptyCheck("Shipping_address2", "Shipping Address2")) {
         $('[name="Shipping_address2"]').focus();
       }
      else if(!EmptyCheck("Shipping_city", "Shipping City")) {
         $('[name="Shipping_city"]').focus();
         
       }
       else if(!ZipCheck("Shipping_zip", "Shipping Zip")) {
         $('[name="Shipping_zip"]').focus();
      }
      else if(!PhoneNumCheck("Shipping_Phone", "Shipping Phone")) {
         $('[name="Shipping_Phone"]').focus();
       }
      else if(!EmptyCheck("cardType")) {
         $('[name="cardType"]').focus();
       }
       else if (!CreditCardNumCheck("cardNumber")) {
        $('[name="cardNumber"]').focus();
       }
      else if(!EmptyCheck("Month", "Month")) {
         $('[name="Month"]').focus();
       }
      else if(!EmptyCheck("Year", "Year")) {
         $('[name="Year"]').focus();
       }
      else if(!CreditSecurityCode("securityCode")) {
         $('[name="securityCode"]').focus();
       }
       else
       {
           write_error("#message_line_checkout", "");
           var ShippingAddress;
           
           ShippingAddress = "\" " + $('[name="Shipping_name"]').val() + "<br>" +
                            $('[name="Shipping_address1"]').val() + "<br>" +
                            $('[name="Shipping_address2"]').val() + "<br>" +
                            $('[name="Shipping_city"]').val() + "<br>" +
                            $('[name="Shipping_zip"]').val() + "<br>" +
                            $('[name="Shipping_Phone"]').val() + "<br>" +
                            "\" ";
           
           window.location.href = "/jadrn026/servlet/proj3/ShowCart?Checkout=1&Address=" + ShippingAddress;
       }
    

        
    });
 
    function CreditCardNumCheck(name)  
    {
        var pattern = /^\d{16}$/
        var cardNum = $('[name='+name+']').val();
        if(pattern.test(cardNum))
        {  
          $('[name='+name+']').css('background-color','white');
           return true;  
        }  
        else
        {
            $('[name='+name+']').css('background-color','#FFB2CC');
            write_error("#message_line_checkout", "Not a valid credit card number!"); 
            return false;  
        }
    }

    function CreditSecurityCode(name)  
    {
        var pattern = /^\d{3}(\d{1})?$/
        var Code = $('[name='+name+']').val();
        if(pattern.test(Code))
        {  
          $('[name='+name+']').css('background-color','white');
           return true;  
        }  
        else
        {
            $('[name='+name+']').css('background-color','#FFB2CC');
            write_error("#message_line_checkout", "Not a valid security code!"); 
            return false;  
        }
    }

  function ZipCheck( name, message)  
    {
        var pattern = /^\d{5}(-\d{4})?$/
        var ZipCode = $('[name='+name+']').val();
        if(pattern.test(ZipCode))
        {  
          $('[name='+name+']').css('background-color','white');
          return true;  
        }  
        else
        {
            $('[name='+name+']').css('background-color','#FFB2CC');
           write_error("#message_line_checkout", "Not a valid "+ message + " code!"); 
            return false;  
        }
    }

      function PhoneNumCheck(name, message)  
    {
        var pattern = /^\d{10}$/
        var PhoneNum = $('[name='+name+']').val();
        if(pattern.test(PhoneNum))
        {  
          $('[name='+name+']').css('background-color','white');
           return true;  
        }  
        else
        {
            $('[name='+name+']').css('background-color','#FFB2CC');
            write_error("#message_line_checkout", "Not a valid "+ message +" Number!"); 
            return false;  
        }
    }

    function EmptyCheck(name, message)  
    {
        var Ctype = $('[name='+name+']').val();
        if(Ctype != "" && Ctype != undefined )
        {  
          $('[name='+name+']').css('background-color','white');
           return true;  
        }  
        else
        {
            $('[name='+name+']').css('background-color','#FFB2CC');
            write_error("#message_line_checkout", "Invalid entry: " + message); 
            return false;  
        }
    }    
    
          
  $('[name="sameas_billadd"]').click(function() {
    if ($('[name="sameas_billadd"]').is(':checked')) 
    {   
        $('[name="Shipping_name"]').val($('[name="Billing_name"]').val()) ;
        $('[name="Shipping_address1"]').val($('[name="Billing_address1"]').val()) ;
        $('[name="Shipping_address2"]').val($('[name="Billing_address2"]').val()) ;
        $('[name="Shipping_city"]').val($('[name="Billing_city"]').val()) ;
        $('[name="Shipping_state_select"]').val($('[name="Billing_state_select"]').val()) ;
        $('[name="Shipping_zip"]').val($('[name="Billing_zip"]').val()) ;
        $('[name="Shipping_Phone"]').val($('[name="Billing_Phone"]').val()) ;
    
    }
    else
    {
        $('[name="Shipping_name"]').val(" ") ;
        $('[name="Shipping_address1"]').val(" ") ;
        $('[name="Shipping_address2"]').val(" ") ;
        $('[name="Shipping_city"]').val(" ") ;
        $('[name="Shipping_state_select"]').val(" ") ;
        $('[name="Shipping_zip"]').val(" ") ;
        $('[name="Shipping_Phone"]').val(" ") ;
    }    
  });   
        
   $('[name="reset"]').on('click', function(e) {
        e.preventDefault();
        ResetForm();
    });
        
    function ResetForm() {
       $('form')[1].reset();
       $('form')[2].reset();
       $('form')[3].reset();
    }    
    



    function write_error(id, msg) {
        $(id).html("<b>"+msg+"</b>");   
    }
     

});
 
 


   
     
    

    
    

         
     
    
    
    
    
    
    
    
    
    
    


