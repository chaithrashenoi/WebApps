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
  


  $('[name="review"]').on('click', function(e) {
        e.preventDefault();

       if ( !CreditCardNumCheck()) {
        $('[name="cardNumber"]').focus();
       }
       //else if(!SecurityCodeCheck()) {}
        //  $('[name="cardNumber"]').focus();
       //}
        
    });
 
  
  function CreditCardNumCheck()  
  {
    var cardno = /^[0-9]{16}$/
    var inputtxt = $('[name="cardNumber"]').val();
    if(inputtxt.value.match(cardno))  
        {  
          return true;  
        }  
        else
        {
          write_error("#message_line_cart", "Not a valid credit card number!"); 
          return false;  
        }
    }
    
   function write_error(id, msg) {
        $(id).html("<b>"+msg+"</b>");   
    }
    
   function CreditSecurityCheck(inputtxt)
     {
     var code = /^[0-9]{3}$/;  
      if(inputtxt.value.match(code))  
        {  
          return true;  
        }  
        else
  
        {
  
          write_error("#message_line_cart", "Not a valid security code!"); 
          $('[name="securityCode"]').focus();

          return false;  
        }
  
    }

    function ZipCode(inputtxt)
     {
     var Zipcode = /^[0-9]{5}$/;  
      if(inputtxt.value.match(Zipcode))  
        {  
          return true;  
        }  
        else
  
        {
  
          write_error("#message_line_cart", "Not a valid Zip code!"); 
          $('[name="securityCode"]').focus();

          return false;  
        }
  
    }
   
    
    
     
  
  } );
  
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  


 





















