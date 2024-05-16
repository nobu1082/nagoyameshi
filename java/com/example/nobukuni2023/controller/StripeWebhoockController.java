package com.example.nobukuni2023.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.nobukuni2023.entity.User;
import com.example.nobukuni2023.repository.UserRepository;
import com.example.nobukuni2023.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Price;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.issuing.AuthorizationCreateParams.VerificationData.ThreeDSecure.Result;
import com.stripe.util.StringUtils;

@Controller
public class StripeWebhoockController<TradeOrder> {
	
	private Object request;
	 private UserRepository userRepository = null;  
	 
	@PostMapping("/stripe/webhook")
	public String webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader,Map<String, String> paymentIntentObject) {
		Stripe.apiKey = "sk_test_51OLEXaJsSQOmZTMCrcb62esT4RG6bjjugAXnoxNd6E7gCTCJPdCGPRK1KgYHvgoro0A3ckUdXMCOoMeZmp6uwDeX00agPzG7vY";
		String endpointSecret = "{{STRIPE_WEBHOOK_SECRET}}";
		Event event = null;
		try {
			event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
		} catch (SignatureVerificationException e) {
			return "";
		}
		switch (event.getType()) {
		case "checkout.session.completed":
			User user = new User();
	        String Subscription = String.valueOf(paymentIntentObject.get("Subscription"));
	        user.setSubscription_Id(Subscription);
	        userRepository.save(user);
	        
			break;
		}

		return "/";
	}
	    
	    public void SubscriptionService(UserRepository userRepository) {
	        this.userRepository = userRepository;  
	    }    
	    public class StripeUtils {

	        public static void createPrice() throws StripeException {
	        	Map<String, Object> priceParams = new HashMap<>();   
	    }
        SessionCreateParams.Builder builder = SessionCreateParams.builder();
        
        // 检查是否是订阅模式的商品　サブスクリプションモデルの製品かどうかを確認する
                    String sigHeader = ((Object) request).getHeader("Stripe-Signature");
            Event event = null;

            try {
                event = Webhook.constructEvent(
                      sigHeader, sigHeader, sigHeader
                );
            } catch (SignatureVerificationException e) {
                // Invalid signature
            }

            // Deserialize the nested object inside the event イベント内のネストされたオブジェクトを逆シリアル化する
            // EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            //StripeObject stripeObject = null;
          
            // Handle the event イベントを処理する
            switch (event.getType()) {            

                // 订阅模式下回调处理 サブスクリプションモードでのコールバック処理
                case "invoice.payment_succeeded": {
                    // Then define and call a function to handle the event payment_intent.succeeded
                    Invoice invoice = (Invoice) stripeObject;
                    log.info("invoice.payment_succeeded : sub_id : {}, order_id: {}", invoice.getSubscription(), invoice.getSubscriptionDetails().getMetadata().get("order_id"));
                    String orderId = invoice.getSubscriptionDetails().getMetadata().get("order_id");

                    TradeOrder tradeOrder = StripeService.getById(orderId);
                    if (! ServiceUtils.isOrderSuccess(tradeOrder.getStatus())) {
                        tradeOrder.setTradeNo(invoice.getSubscription());   // 自动订阅存储的是订阅ID 自動サブスクリプションにはサブスクリプション ID が保存されます
                        tradeOrderService.successOrder(tradeOrder);
                    }

                    break;
                }
            }















































