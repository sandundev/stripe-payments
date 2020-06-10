package com.baeldung.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) throws StripeException {
        log.info("in /checkout - page");



        Map<String, Object> params = new HashMap<String, Object>();

        ArrayList<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        params.put("payment_method_types", paymentMethodTypes);

        ArrayList<HashMap<String, Object>> lineItems = new ArrayList<>();
        HashMap<String, Object> lineItem = new HashMap<String, Object>();
        HashMap<String, Object> priceData = new HashMap<String, Object>();
        priceData.put("product", "prod_HRVm2Qx9LyxJHD");
        priceData.put("unit_amount", 1500);
        priceData.put("currency", "gbp");
        lineItem.put("price_data", priceData);
        lineItem.put("quantity", 1);
        lineItems.add(lineItem);
        params.put("line_items", lineItems);
        params.put("mode", "payment");

        params.put("success_url", "https://example.com/success");
        params.put("cancel_url", "https://example.com/cancel");

        Session session = Session.create(params);
        model.addAttribute("sessionId",  session.getId());
        model.addAttribute("stripePublicKey",  stripePublicKey);
        return "checkout";
    }
}
