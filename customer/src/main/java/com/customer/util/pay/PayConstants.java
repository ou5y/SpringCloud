/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.customer.util.pay;

/**
 * 
 * 支付加密常量
 */
public class PayConstants {

    public static final String SIGN_TYPE                      = "sign_type";

    public static final String SIGN_TYPE_RSA                  = "RSA";
    /**
     * sha256WithRsa 算法请求类型
     */
    public static final String SIGN_TYPE_RSA2                 = "RSA2";
    public static final String SIGN_ALGORITHMS                = "SHA1WithRSA";
    public static final String SIGN_SHA256RSA_ALGORITHMS      = "SHA256WithRSA";
    /** 默认时间格式 **/
    public static final String DATE_TIME_FORMAT               = "yyyy-MM-dd HH:mm:ss";
    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8                   = "UTF-8";

    /** GBK字符集 **/
    public static final String CHARSET_GBK                    = "GBK";

    //uat
//    public static final  String PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDGD8oPkzy2XmgDB17fAtB6Sw/T2WrerZEXd8Q5aTPZayTvh2wirpXKGFbOOXOZu1Uf2G8kiOdeiT/jlxVNP7we61/4sxzXMBpgcEe9MqUWr2HrFHTChzuZKsfkuKVdTrOHBTSVhkMR1nIpQTLv5/nUq7KFDJAqR1x30gHYnup1pMYMNGvXG3vbZHWEfpujZiO60tyoCVeAI2+BHrumH9Bf5v8ey5vs2ApET1HaftrXyoGmtF5iN4Ys9n3OOEZnri7/oF1HDefCNJq5wca8ntoyPPHwtBZlKgjLWqvlO/LwlK77Oa3ZZb1GAsc9NOVStuxmW+uZDhFehBJ7HSxNCozJAgMBAAECggEAS5bzEwaqgR9+qslBV/OHBWVoYP31X472dPJsnUydUTknG5OfnYzr9flcB9C9YcqxLjo4EMV/KAFRtNaQdq8AaPyD4DLbqvTc3Dl956NQhlvhDwr7lNJv2IEPUSd3VSkYM0u1Seu35TLm1v/fJNhv7M6qj0TAMKKTk9c+hSIBcWwIr6gDxEagpGS+lJtnnXAgLeTjFse9sB8Prs63oGbNqAsrHbDkvum3KhgxTT+PYEoE3KlhD8DZJVisK+Sxxt1yFIHDXZaILwFQjNw4eD5ROHYnlPsknNo8RpvMMb28wXxHTbKwtfhAjFqrbO2vMVf84KBn+7Di554ya87Q4JR50QKBgQD5mmM4o3hTlowUE0ePKSOzXg2dRnVjUSGMgrJfnj1YI3cHvJSqT/VER9WnkdH0p7CjS/YTAE3/Hda/VlbrteFDGOYLWoj5r3GNWpXDhKyeceOmz/+D44JGr2TkuP/00TCRbsX43Vv/E5e54ryie1DsqI3Ta3/BvinzxUwBhVDPmwKBgQDLIz7YG0DZoe3s/nrX+8GqOzq8f47p075gyM7LzpWQCMMf8l2RCVpByrLIe62hNHemsGiWs3yd9pUFx9PzHrWy2fS+Qjqa6F+SXVzXPm1d8vUcq1epjlFSVVuRZwFCNuFYCDw1SOoHg9DKivkEatVAizCMu/ACU2nxaOCis11FawKBgDLuowKCsOH1nV3XVUnMLkHClmqaXwfGHV9rp/RE+OWMTxSgEYHzUOv+8IUCB2Kx+SRJMsxLyp6dOx3OA2yTr4SehwqYDc1NVJFwg5Di+MpYGQkH9m62NPYxghf9/BS7vvpeLT2MBeK138cJkk5hdXDlMBONIwOoxyy7/Lduub+zAoGBAK4WnZQM/y3ENahmixawfk+KhRLoaqz/t2cwkPFfMRQIPlkRl5hloIFD2qYwQCQoV+Z1xpxoZALoQNx8Xas8hVEqszGehqUdQlKoSpl4ubfLML8KJI1+FzltR5U75ns9ly+flyG/RDl+gSAjq7daaznuT4Q8yDrmC6RFUkZ9WxDTAoGBANE82zZ0AyLqciL+eXDomQb4k/jr9Asl+4FGgk8g0YXOe7pzO58sHaJ+ZmkPi5huIM0I7PMNZ6IIqYtk0x7OmxL/Dpe8v0sSzx2K3EY6fpquTOAlOOne1+AxCmJAZqwRpgP2zPGg0EqEfHk6wcg7nPke7hmYBm8K8xKY4dHOmlt4";

    //prod
     public static final  String PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPUiy+YK4onzOWU06RDL3PWxM/ZJEOREuHiszKC0+zcjS2Kb8NGB7SCFH+8GlfbSAs8muQjJI74KcUuo0tVswCvg6mdZtlCOY8FIdVcxYHH9TKsifhkD9h8/F0U4kZ4wL3ySvfBRs4d7Fi0qGmmpXCqKMC0rJiWWY9l85MFUcY1FX7KXt7yCuduQMnCyAJefUMnmnWe6sW52lKixnVaroszOIKzPY7VHS0GF0UEsPtcti3w7O1F8IPZOVCHWKEfylPw6Sz5pLgWHJQWdYt/6zowCoCink6R/mNk34+YiGsOy/LiuzGyXsMqFZmEvy4vYfziogeGfv+h1P+9jBq01inAgMBAAECggEAWajX0vWanlESLb3KDdYBvIHGZccdM+antZCwkslGwAT3UuqE2cU4Kr8HxMxTr14OXe/+OLyoEwt0mvQls7J0CFcrxRkX8LoDhpx1GZDYu/Y+4dtYNbVO4ZGBgPttbqNkzOyuJGQGZmoCr8cDV72naNKkv/Rdokc5PdkFNvWst6vNAVw4cGaF5r5CJjiKJjnuomwO5zhy1gGnXoTBa22RCtdPsypAEhEqLyosqV3VHo/stxSCerPImvIbbnHMTTyGSDwTR/XGaRmF+Q7AYSrT/WsFm3MorVJ2bUXvjNwt6FyYtfDRZZ289QoDLCNUCPGAqHyMiCUE7nebppj+vz7xaQKBgQD+hiN7NoQdUrefA+6Ojog3vVg+8aBhsnIxY3lpMzmNSlypX2tTNkO80xpAHfCn6SmGLxVh9VAbhDVmGWKoZe2UHa4YGydoGSgaTMO9OeUyz2598T3ItUy2lcgBU53cIJWgN/hUbNGgJXmEW/dpgZgLG7IecaKoJVFp7H8j3DTuYwKBgQCQJvJFZp8vmNyO4x/c4on1Oq1Ho83Hy1HDJNpuM7Dul17A3J9QaLGoP3m1g0iDem+nJaYZQgKvqWVknlDl69Y6CLCqwu/+SKJ/ReMtdzvIIRr0kHlD2eZW3pEOpF92fUBDWhfCjWMbCxrpwOMFhgyYWSiPtUkYmHmhYKfBu6vt7QKBgQD1bqvwOFWVYRongEcduFuqRC9AIwegrc5nyaBdzUOF91r7CEQZ7Y3MG1SI11Yk1DVyaxyXZVJl9kr3tSrpb8B0v1oNbMHHI9sD4VoushFNubC2SVZmoEzlE/HVNahXiCRAZzhTUMujPf+7nmMpngnbTE2qBbHvqmyjU6YWElYIOQKBgGKAGmBN5Uxy2ZNRo34d1kMZmzoutfhl+DKuRguzip0eFgMXPk2FnI/o/9OGJ9CfoxfF5B+yOX+q8ru0ancRlcsGESI+HhvVqiPpr3hr1w/Df1XKOvKgPILzho9IxmqC35ymPUIVtrjN98/um7I0jPq1QcAhEheaaNJa54a8JJlpAoGBAJ7d9gEgoTDCnhM8dDkCQR1E3WkfNdnSrc2IqJwfEJTDvbflSIQqriVHIPLzwjrgJZG/xsKH/2MawIPhRpVXIrT7cohVUn+55LFxzkAb5xCQG2Ujmcs1eO+ylORcondO6ifj0+X7zUHqRgWMqd67BZB/GRKfhx7bkG4DObGzoJ57";
}
