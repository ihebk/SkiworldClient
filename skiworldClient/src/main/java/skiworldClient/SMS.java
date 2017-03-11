package skiworldClient;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
		// Find your Account Sid and Token at twilio.com/user/account
		  public static final String ACCOUNT_SID = "ACb3dbe342d513746cc5aa376cd298935b";
		  public static final String AUTH_TOKEN = "9b4e62c430071d7a7444ddb4a3c9f565";

		  public static void main(String[] args,String receiver,String text) {
		    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		    Message message = Message.creator(new PhoneNumber(receiver),
		        new PhoneNumber("+12088744759"), text).create();

		    System.out.println(message.getSid());
		  }
}
