package cn.imovie.mockserver.notice;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Licoy.cn
 * @version 1.0 / qrcode
 */
@Api("二微码")
@Controller
public class QrController {
    int width=200;
    int height=200;
    String format="png";
    private static final Logger logger = LoggerFactory.getLogger(QrController.class);
    @ApiOperation("生成二微码")
    @GetMapping("/qr")
    public void get(
//            @RequestParam(name = "w",defaultValue = "200",required = false) int width,
//                    @RequestParam(name = "h",defaultValue = "200",required = false) int height,
//                    @RequestParam(name = "f",defaultValue = "png",required = false) String format,
//                    @RequestParam(name = "c",defaultValue = "") String content,
                    HttpServletResponse response, @RequestParam String url) throws Exception {
        logger.info("生成二微码链接内容URL："+url);
        ServletOutputStream out = response.getOutputStream();
        Map<EncodeHintType,Object> config = new HashMap<>();
        config.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        config.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        config.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE,width,height,config);
        MatrixToImageWriter.writeToStream(bitMatrix,format,out);
        logger.info("二维码生成完毕，已经输出到页面中");

    }





}