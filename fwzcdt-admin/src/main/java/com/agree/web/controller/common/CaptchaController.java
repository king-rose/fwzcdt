/*
package com.agree.web.fwlb.controller.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.agree.common.annotation.Log;
import com.agree.system.service.ISysConfigService;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;
import com.agree.common.constant.Constants;
import com.agree.common.core.domain.AjaxResult;
import com.agree.common.core.redis.RedisCache;
import com.agree.common.utils.sign.Base64;
import com.agree.common.utils.uuid.IdUtils;

*/
/**
 * 验证码操作处理
 *
 * @author ruoyi
 *//*

@RestController
public class CaptchaController
{
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    // 验证码类型
    @Value("${ruoyi.captchaType}")
    private String captchaType;

    @Autowired
    private ISysConfigService configService;

    */
/**
     * 生成验证码
     *//*

  */
/*  @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType))
        {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if ("char".equals(captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return AjaxResult.error(e.getMessage());
        }

        AjaxResult ajax = AjaxResult.success();
        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }*//*



    */
/**
     * 生成验证码
     *//*

    @GetMapping("/captchaImage")
    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        AjaxResult ajax = AjaxResult.success();
        boolean captchaOnOff = configService.selectCaptchaOnOff();
        ajax.put("captchaOnOff", captchaOnOff);
        if (!captchaOnOff)
        {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        //默认动态字符串
        Captcha captcha = new GifCaptcha(115, 42);

        if ("spec".equals(captchaType)){
            captcha = new SpecCaptcha(115, 42);  //创建中文验证码
        }
        if ("chinese".equals(captchaType)){
            captcha = new ChineseGifCaptcha(115, 42);  //创建中文验证码
        }
        if ("math".equals(captchaType)){
            captcha = new ArithmeticCaptcha(115, 42);  //创建中文验证码
        }

        String code = captcha.text(); //得到算术验证码的计算值
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);//存入redis

        ajax.put("uuid", uuid);
        ajax.put("img", captcha.toBase64());  //获得图片的base64编码
        return ajax;
    }

}
*/
