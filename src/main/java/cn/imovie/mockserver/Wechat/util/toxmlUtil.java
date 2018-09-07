package cn.imovie.mockserver.Wechat.util;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.sax.SAXResult;
import java.io.ByteArrayOutputStream;

public class toxmlUtil {
    /**
     * Java对象转换为CDATA包裹XML
     *
     * @param clazz
     * @param obj
     * @return
     * @throws Exception
     */
    public static String ojbectToXmlWithCDATA(Class clazz, Object obj) throws Exception {

        JAXBContext context = JAXBContext.newInstance(clazz);
        // 配置OutputFormat处理CDATA
        OutputFormat of = new OutputFormat();
        of.setCDataElements(new String[]{"^code", "^msg","dgf","df"});
        of.setPreserveSpace(true);
        of.setIndenting(true);
        // 创建序列化
        ByteArrayOutputStream op = new ByteArrayOutputStream();
        XMLSerializer serializer = new XMLSerializer(op, of);
        SAXResult result = new SAXResult(serializer.asContentHandler());
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        mar.marshal(obj, result);

        return op.toString("utf-8");
    }
}
