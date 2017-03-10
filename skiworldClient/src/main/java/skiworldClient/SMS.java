package skiworldClient;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
		// Find your Account Sid and Token at twilio.com/user/account
		  public static final String ACCOUNT_SID = "AC41e09925f5224c358d64c7703217ab6d";
		  public static final String AUTH_TOKEN = "2206d126c3a60b115fce51269e4f0ba0";

		  public static void main(String[] args) {
		    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		    Message message = Message.creator(new PhoneNumber("0021624056027"),
		        new PhoneNumber("00"), "This ").create();

		    System.out.println(message.getSid());
		  }
}
