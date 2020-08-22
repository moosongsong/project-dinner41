package kr.co.dinner41.service.login;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.dinner41.dao.UserDao;
import kr.co.dinner41.exception.login.LoginException;
import kr.co.dinner41.exception.login.SendEmailFailedException;
import kr.co.dinner41.exception.user.UserException;
import kr.co.dinner41.exception.user.UserUpdateFailedException;
import kr.co.dinner41.vo.UserVO;

@Service("sendTempPasswordService")
public class SendTempPasswordServiceImpl implements SendTempPasswordService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	private String makeTempPassword() {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
		    int rIndex = rnd.nextInt(4);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // @ + A-Z
		        temp.append((char) ((int) (rnd.nextInt(27)) + 64));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    case 3:
		    	temp.append((char) ((int)(rnd.nextInt(4))+35));
		    	break;
		    }
		}
		return temp.toString();
	}

	@Override
	public void execute(UserVO user) throws LoginException,UserException {
		final String bodyEncoding = "UTF-8";
		String subject = "Temporary Password for 1인의 만찬";
		String fromEmail = "teamDinner41@gmail.com";
		String fromUsername = "team Dinner41";
		String toEmail = user.getEmail();
		
		String tempPassword=makeTempPassword();
		String dinner41URL="https://www.naver.com/";

		final String userEmail=fromEmail;
		final String password = "#kingju1011";

		StringBuffer sendBuffer = new StringBuffer();
		sendBuffer.append("<div style=\"border:2px solid black; margin 10px;\" >");
		sendBuffer.append("<h2 style=\"text-align:center\">TEAM DINNER41</h2>");
		sendBuffer.append("<h3 >&nbsp;&nbsp;&nbsp;").append(toEmail).append("회원님의 임시비밀번호가 발급되었습니다.</h3>");
		sendBuffer.append("<h1 style=\"text-align:center\">").append(tempPassword).append("</h1>");
		sendBuffer.append("<h3>&nbsp;&nbsp;&nbsp;<a href=\"").append(dinner41URL).append("\">").append(dinner41URL).append("</a>(1인의 만찬) 로 이동 후</h3>");
		sendBuffer.append("<h3>&nbsp;&nbsp;&nbsp;임시비밀번호로 로그인을 진행하세요</h3>");
		sendBuffer.append("<h3 style=\"color:red\">&nbsp;&nbsp;&nbsp;반드시 새로운 비밀번호로 변경해주세요</h3>");
		sendBuffer.append("</div>");
		String html = sendBuffer.toString();

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userEmail, password);
				}
			};

			Session session = Session.getInstance(props, auth);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, fromUsername));
			message.setRecipient(RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			message.setSentDate(new Date());

			Multipart mParts = new MimeMultipart();
			MimeBodyPart mTextPart = new MimeBodyPart();

			mTextPart.setText(html, bodyEncoding, "html");
			mParts.addBodyPart(mTextPart);

			message.setContent(mParts);

			MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");

			CommandMap.setDefaultCommandMap(MailcapCmdMap);

			Transport.send(message);
			System.out.println("이메일 전송 완료");
			
			user.setPassword(tempPassword);
			userDao.update(user);

		} catch (MessagingException|UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			throw new SendEmailFailedException();
		}
		catch(UserException e){
			System.out.println(e.getMessage());
			throw new UserUpdateFailedException();
		}


	}

}
