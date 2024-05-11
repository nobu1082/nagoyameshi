package com.example.nobukuni2023.service;

import org.springframework.stereotype.Service;

@Service
public class StripeService {
	 /* @Value("${stripe.api-key}")
    private String stripeApiKey;
	
	
	public String createStripeSession(HttpServletRequest httpServletRequest) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		
		String requestUrl = new String(httpServletRequest.getRequestURL());
		
		SessionCreateParams params = new SessionCreateParams.Builder()
				//.setSuccessUrl("https://example.com/success.html?session_id={CHECKOUT_SESSION_ID}")
				.setSuccessUrl("https://buy.stripe.com/test_cN23dM5EI6X3bni9AB")
				.setCancelUrl("https://example.com/canceled.html")
				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
				.addLineItem(new SessionCreateParams.LineItem.Builder()
						// For metered billing, do not pass quantity
						.setQuantity(1L)
						.setPrice("price_1PCcVZJsSQOmZTMC9yEmSw84")
						.build())
				.build();
		
		
			Session session = Session.create(params);
			
			  return session.getUrl();
        
		/*	↑AI先生の修正案
			return "/user";
		} catch (StripeException e) {
			e.printStackTrace();
			return "/user";
		} */
/*	} 
	
	 public void processSessionCompleted(Event event) {
         Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
         optionalStripeObject.ifPresent(stripeObject -> {
             Session session = (Session)stripeObject;
             SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("payment_intent").build();
 
             try {
                 session = Session.retrieve(session.getId(), params, null);
                 Map<String, String> paymentIntentObject = session.getPaymentIntentObject().getMetadata();
             } catch (StripeException e) {
                 e.printStackTrace();
             }
         });
     }  */  
	
	
	
}
