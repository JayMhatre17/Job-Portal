package com.jobportal.utility;

public class Data {
    public static String getMessageBody(String otpCode, String name) {

        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "  <meta charset='UTF-8'>" +
                "  <title>OTP Verification</title>" +
                "  <style>" +
                "    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }" +
                "    .container { background-color: #ffffff; max-width: 600px; margin: 40px auto; padding: 30px; border-radius: 8px;" +
                "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }" +
                "    .otp { font-size: 32px; font-weight: bold; color: #2d89e5; margin: 20px 0; letter-spacing: 4px; }" +
                "    .footer { font-size: 12px; color: #777; margin-top: 30px; text-align: center; }" +
                "  </style>" +
                "</head>" +
                "<body>" +
                "  <div class='container'>" +
                "    <h2>Hello, " + name + "</h2>" +
                "    <p>Thank you for using <strong>JobPortal</strong>. Please use the following One-Time Password (OTP) to complete your verification:</p>" +
                "    <div class='otp'>" + otpCode + "</div>" +
                "    <p>This OTP is valid for the next <strong>10 minutes</strong>. Please do not share it with anyone.</p>" +
                "    <p>Regards,<br><strong>JobPortal Team</strong></p>" +
                "    <div class='footer'>" +
                "      If you did not request this OTP, please ignore this email or contact our support.<br><br>" +
                "      &copy; " + java.time.Year.now() + " JobPortal. All rights reserved." +
                "    </div>" +
                "  </div>" +
                "</body>" +
                "</html>";
    }
}
