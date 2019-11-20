package com.pxz.pxznetwork;

/**
 * 类说明：json格式化
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2019/11/12 14:40
 */
public class JsonUtil {
    /**
     * 格式化json字符串
     *
     * @param jsonStr 需要格式化的json串
     * @return 格式化后的json串
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            //遇到{ [换行，且下一行缩进
            if (current == '{' || current == '[') {
                sb.append(current);
                sb.append('\n');
                indent++;
                addIndentBlank(sb, indent);
            } else if (current == '}' || current == ']') {
                sb.append('\n');
                indent--;
                addIndentBlank(sb, indent);
                sb.append(current);
            } else if (current == ',') {
                sb.append(current);
                if (last != '\\') {
                    sb.append('\n');
                    addIndentBlank(sb, indent);
                }
            } else {
                sb.append(current);
            }
        }
        return sb.toString();
    }

    /**
     * 添加space
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    /**
     * http 请求数据返回 json 中中文字符为 unicode 编码转汉字转码
     *
     * @return 转化后的结果.
     */
    public static String unicodeToUTF_8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        if (aChar == '0' || aChar == '1' || aChar == '2' || aChar == '3' || aChar == '4' || aChar == '5'
                                || aChar == '6' || aChar == '7' || aChar == '8' || aChar == '9') {
                            value = (value << 4) + aChar - '0';
                        } else if (aChar == 'a' || aChar == 'b' || aChar == 'c' || aChar == 'd' || aChar == 'e' || aChar == 'f') {
                            value = (value << 4) + 10 + aChar - 'a';
                        } else if (aChar == 'A' || aChar == 'B' || aChar == 'C' || aChar == 'D' || aChar == 'E' || aChar == 'F') {
                            value = (value << 4) + 10 + aChar - 'A';
                        } else {
                            throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
}