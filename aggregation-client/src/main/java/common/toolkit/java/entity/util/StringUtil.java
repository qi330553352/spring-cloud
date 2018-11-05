package common.toolkit.java.entity.util;
import common.toolkit.java.entity.constant.BaseConstant;
import common.toolkit.java.entity.constant.EmptyObjectConstant;
import common.toolkit.java.entity.exception.NotExpectedFormatedException;
import common.toolkit.java.entity.util.collection.CollectionUtil;
import org.apache.commons.lang.StringUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.toolkit.java.entity.constant.EmptyObjectConstant.EMPTY_STRING;
import static common.toolkit.java.entity.constant.RegExpConstant.PATTERN_OF_CONTAINS_IP;

/**
 * Description: String Utils
 * @author nileader / nileader@gmail.com
 * @Date Feb 10, 2012
 */
public class StringUtil {

    /**
     * Check String is equals. targetStr compare with compareStrArray, and
     * return true if equals one or more
     * @param targetStr
     *            寰呮瘮杈冪殑瀛楃涓�
     * @param compareStrArray
     *            瑕佹瘮杈冪殑涓�釜鎴栧涓瓧绗︿覆鏍囧噯
     * @return
     */
    public static boolean containsIgnoreCase(final String originalStr, final CharSequence targetStr) {

        if (null == originalStr) {
            return false;
        }

        String originalStrCaps = originalStr.toUpperCase();
        String targetStrCaps = targetStr.toString().toUpperCase();
        return originalStrCaps.contains(targetStrCaps);
    }

    /**
     * Check if string contains ip,姝ゆ柟娉曚笉鑳藉畬鍏ㄥ垽鏂璱p鏄惁鍚堟硶
     * @return
     */
    public static boolean containsIp(String originalStr) {

        if (StringUtil.isBlank(originalStr))
            return false;

        Matcher match = PATTERN_OF_CONTAINS_IP.matcher(originalStr);
        return match.matches();
    }

    /**
     * Description: Remove {_, -, @, $, #, /, &} in string and make letter after
     * this uppercase.<br>
     * e.g. ni_lea-der@gmail./com -> niLeaDerGmail.Com
     * @param @param inputString
     * @param @param firstCharacterUppercase The first letter is need uppercase.
     * @return String
     * @throws
     */
    public static String convertToCamelCaseString(String inputString, boolean firstCharacterUppercase) {
        if (null == inputString) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            switch (c) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;

                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(c);
                    }
                    break;
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        } else {
            sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        }

        return sb.toString();
    }

    /**
     * Return Default if originalStr is empty.
     * @param originalStr
     *            寰呯‘璁ゅ�
     * @param defaultStr
     *            榛樿鍊�
     * @return 濡傛灉originalStr涓虹┖锛岄偅涔堝氨杩斿洖defaultStr
     */
    public static String defaultIfBlank(String originalStr, String defaultStr) {
        if (StringUtil.isBlank(originalStr)) {
            return defaultStr;
        }
        Collections.emptyList();
        return originalStr;
    }

    /**
     * Check String is equals Ignore Case. targetStr compare with
     * compareStrArray, and return true if equals all
     * @param targetStr
     *            寰呮瘮杈冪殑瀛楃涓�
     * @param compareStrArray
     *            瑕佹瘮杈冪殑涓�釜鎴栧涓瓧绗︿覆鏍囧噯
     * @return true if targetStr same with every string in compareStrArray
     */
    public static boolean equalsIgnoreCaseAll(String targetStr, String... compareStrArray) {

        if (StringUtil.isBlank(targetStr) || null == compareStrArray || 0 == compareStrArray.length) {
            return false;
        }
        for (int i = 0; i < compareStrArray.length; i++) {
            if (!targetStr.equalsIgnoreCase(compareStrArray[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check String is equals. targetStr compare with compareStrArray, and
     * return true if equals one or more
     * @param targetStr
     *            寰呮瘮杈冪殑瀛楃涓�
     * @param compareStrArray
     *            瑕佹瘮杈冪殑涓�釜鎴栧涓瓧绗︿覆鏍囧噯
     * @return true if targetStr same with string in compareStrArray one at
     *         least
     */
    public static boolean equalsIgnoreCaseOne(String targetStr, String... compareStrArray) {

        if (StringUtil.isBlank(targetStr) || null == compareStrArray || 0 == compareStrArray.length) {
            return false;
        }
        for (int i = 0; i < compareStrArray.length; i++) {
            if (targetStr.equalsIgnoreCase(compareStrArray[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 閫氳繃姝ｅ垯琛ㄨ揪鏂瑰紡锛屾壘鍑轰竴涓瓧绗︿覆涓墍鏈夋寚瀹氱殑瀛愪覆
     * @param @param originalStr 瀛楃涓�
     * @param @param regex 寰呮煡鎵惧瓙涓茬殑姝ｅ垯琛ㄨ揪寮�
     * @return List<String> 瀛愪覆闆嗗悎
     *
     *         <pre>
     * 	瀵�/1.1.1.1:sid=0x2337c7074dofj02e,37775[1](queued=0,recved=6,sent=7,sid=0x2337c7074f1102e,sdlfjle,dsfe鐨勭粨鏋滄槸锛�
     * [sid=0x2337c7074dofj02e, , sid=0x2337c7074f1102e, ]
     * </pre>
     */
    public static List<String> findAllByRegex(String originalStr, String regex) {

        if (StringUtil.isBlank(originalStr) || StringUtil.isBlank(regex))
            return null;

        List<String> targetStrList = new ArrayList<String>();
        final Pattern patternOfTargetStr = Pattern.compile(regex, Pattern.CANON_EQ);
        final Matcher matcherOfTargetStr = patternOfTargetStr.matcher(originalStr);
        /** 寮�瑙ｆ瀽 */
        while (matcherOfTargetStr.find()) {
            targetStrList.add(StringUtil.trimToEmpty(matcherOfTargetStr.group()));
        }
        return targetStrList;
    }

    /**
     * 閫氳繃姝ｅ垯琛ㄨ揪鏂瑰紡锛屾壘鍑轰竴涓瓧绗︿覆涓涓�釜鎸囧畾鐨勫瓙涓�
     * @param @param originalStr 瀛楃涓�
     * @param @param regex 寰呮煡鎵惧瓙涓茬殑姝ｅ垯琛ㄨ揪寮�
     * @return List<String> 瀛愪覆闆嗗悎
     *
     *         <pre>
     * 	瀵�/1.1.1.1:sid=0x2337c7074dofj02e,37775[1](queued=0,recved=6,sent=7,sid=0x2337c7074f1102e,sdlfjle,dsfe鐨勭粨鏋滄槸锛�
     * sid=0x2337c7074dofj02e,
     * </pre>
     */
    public static String findFirstByRegex(String originalStr, String regex) {

        if (StringUtil.isBlank(originalStr) || StringUtil.isBlank(regex))
            return EMPTY_STRING;

        final Pattern patternOfTargetStr = Pattern.compile(regex, Pattern.CANON_EQ);
        final Matcher matcherOfTargetStr = patternOfTargetStr.matcher(originalStr);
        /** 寮�瑙ｆ瀽 */
        if (matcherOfTargetStr.find()) {
            return StringUtil.trimToEmpty(matcherOfTargetStr.group());
        }
        return EMPTY_STRING;
    }

    /**
     * 鐢熸垚绌虹櫧琛�
     * @param lines
     *            琛屾暟
     */
    public static String generateLineBlank(int lines) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lines; i++) {
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * make first letter lower case for str
     * @return Same letter, but the first letter is lower case.
     */
    public static String makeFirstLetterLowerCase(String str) {
        String firstLetter = str.substring(0, 1);
        return firstLetter.toLowerCase() + str.substring(1, str.length());
    }

    /***
     * check if orginalStr is null or empty. <br>
     * If have more than one originalStr, use isBlank(String...
     * originalStrArray)
     * @param originalStr
     *            寰呯‘璁ゅ�
     * @return true or false;
     */
    public static boolean isBlank(String originalStr) {
        if (null == originalStr) {
            return true;
        }
        if (originalStr.contains(BaseConstant.WORD_SEPARATOR)) {
            return false;
        }
        return trimToEmpty(originalStr).isEmpty();
    }

    /**
     * 判断字符串是否为空串，向后兼容. <br/>
     * @author zhang
     * @param originalStr
     * @return
     */
    public static boolean isEmpty(String originalStr) {
        return StringUtils.isEmpty(originalStr);
    }

    /***
     * check if orginalStr is null or empty
     * @param String
     *            ... originalStrArray
     * @return true if have one blank at least.
     */
    public static boolean isBlank(String... originalStrArray) {

        if (null == originalStrArray || 0 == originalStrArray.length)
            return true;
        for (int i = 0; i < originalStrArray.length; i++) {
            if (isBlank(originalStrArray[i]))
                return true;
        }
        return false;
    }

    /**
     * check the originalStr is contain the whitespace
     * @param originalStr
     * @return true if contain whitespace
     */
    public static boolean isContainWhitespace(String originalStr) {

        if (StringUtil.isBlank(originalStr)) {
            return true;
        }
        int strLen = originalStr.length();
        for (int i = 0; i < strLen; i++) {
            char ch = originalStr.charAt(i);
            if (Character.isWhitespace(ch)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串连接，使用指定分隔符
     * @param subStr
     * @return
     */
    public static String join(String... subStrs) {

        if (null == subStrs || 0 == subStrs.length) {
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder();
        for (String subStr : subStrs) {
            sb.append(subStr).append(BaseConstant.WORD_SEPARATOR);
        }
        String sbStr = sb.toString();
        if (sbStr.endsWith(BaseConstant.WORD_SEPARATOR)) {
            sbStr = StringUtil.replaceLast(sbStr, BaseConstant.WORD_SEPARATOR, "");
        }
        return sbStr;
    }

    /**
     * Description: Replaces last substring of this string that matches the
     * given regular expression with the given replacement.<br>
     * Do not worry about null pointer
     * @param @param regex
     * @param @param replacement
     * @return String
     * @throws
     */
    public static String replaceAll(String originalStr, String replacement, String regex) {
        return StringUtil.trimToEmpty(originalStr).replaceAll(regex, replacement);
    }

    public static String replaceAll(String originalStr, String replacement, String... regexArray) {

        if (0 == regexArray.length)
            return originalStr;

        for (String regex : regexArray) {
            originalStr = StringUtil.replaceAll(originalStr, replacement, regex);
        }

        return originalStr;
    }

    /**
     * Description: Replaces last substring of this string that matches the
     * given regular expression with the given replacement.
     * @param @param regex
     * @param @param replacement
     * @param @return
     * @return String
     * @throws
     */
    public static String replaceLast(String originalStr, String regex, String replacement) {

        if (StringUtil.isBlank(originalStr))
            return EMPTY_STRING;

        int index = originalStr.lastIndexOf(regex);
        if (-1 == index)
            return originalStr;

        // 鍏堝瓨鍌ㄨ繖涓猧ndex涔嬪墠鐨勬墍鏈塻tr
        String temp = originalStr.substring(0, index);
        String temp2 = originalStr.substring(index, originalStr.length());

        temp2 = temp2.replaceFirst(regex, replacement);

        originalStr = temp + temp2;

        return originalStr;
    }

    /**
     * 用一个map中的值来替换占位符 ${key}
     * @param originalStr
     *            a string such as :
     *            "select * from table where id=${id}, name=${name}, gender=${gender}"
     */
    public static String replacePlaceholder(String originalStr, Map<String, String> values) {
        if (CollectionUtil.isBlank(values)) {
            return originalStr;
        }
        if (StringUtil.isBlank(originalStr))
            return EMPTY_STRING;

        for (String key : values.keySet()) {
            String value = StringUtil.trimToEmpty(values.get(key));
            String regex = "\\$\\{" + key + "\\}";
            originalStr = originalStr.replaceAll(regex, value);
        }
        return originalStr;
    }

    /**
     * Description: Replaces all {n} placeholder use params
     * @param originalStr
     *            a string such as :
     *            "select * from table where id={0}, name={1}, gender={3}"
     * @param replacementParams
     *            real params: 1,yinshi.nc,male
     * @note n start with 0
     */
    public static String replaceSequenced(String originalStr, Object... replacementParams) {

        if (StringUtil.isBlank(originalStr))
            return EMPTY_STRING;
        if (null == replacementParams || 0 == replacementParams.length)
            return originalStr;

        for (int i = 0; i < replacementParams.length; i++) {
            String elementOfParams = replacementParams[i] + EMPTY_STRING;
            if (StringUtil.trimToEmpty(elementOfParams).equalsIgnoreCase("null"))
                elementOfParams = EMPTY_STRING;
            originalStr = originalStr.replace("{" + i + "}", StringUtil.trimToEmpty(elementOfParams));
        }

        return originalStr;
    }

    /**
     * 璁剧疆鍓嶇紑锛屽鏋滆繖涓瓧绗︿覆宸茬粡鏄繖涓墠缂�簡锛岄偅涔堝氨涓嶄綔浠讳綍鎿嶄綔銆� TODO none test
     */
    public static String setPrefix(String originalStr, String prefix) {
        originalStr = StringUtil.trimToEmpty(originalStr);
        prefix = StringUtil.trimToEmpty(prefix);
        if (!originalStr.startsWith(prefix)) {
            originalStr = prefix + originalStr;
        }
        return originalStr;
    }

    /**
     * 鍒嗗壊瀛楃涓诧紝濡傛灉涓嶆槸鎸囧畾闀垮害锛屾姏鍑哄紓甯�
     * @param originalStr
     *            鍘熷瓧绗︿覆
     * @param regex
     *            鍒嗗壊绗�
     * @param fixedLength
     *            鎸囧畾fixed闀垮害
     * @return 鍒嗗壊鍚庣殑瀛楃涓叉暟缁�
     * @throws NotExpectedFormatedException
     */
    public static String[] splitWithFixedLength(String originalStr, String regex, int fixedLength)
            throws NotExpectedFormatedException {

        if (StringUtil.isBlank(regex)) {
            return EmptyObjectConstant.EMPTY_STRING_ARRAY;
        }

        if (StringUtil.isBlank(originalStr)) {
            if (0 == fixedLength)
                return EmptyObjectConstant.EMPTY_STRING_ARRAY;
            throw new NotExpectedFormatedException("鎸囧畾fixedLength涓猴細" + fixedLength + ", 浣嗘槸originalStr涓虹┖");
        }

        String[] strArray = originalStr.split(regex);
        if (fixedLength != strArray.length) {
            throw new NotExpectedFormatedException("鎸囧畾fixedLength涓猴細" + fixedLength + ", 浣嗘槸originalStr涓�"
                    + originalStr);
        }
        return strArray;
    }

    /**
     * 鍒嗗壊瀛楃涓诧紝濡傛灉灏忎簬鎸囧畾闀垮害锛�=leastLength ok锛夛紝鎶涘嚭寮傚父
     * @param originalStr
     *            鍘熷瓧绗︿覆
     * @param regex
     *            鍒嗗壊绗�
     * @param leastLength
     *            鎸囧畾鐨勬渶灏弆east闀垮害
     * @return 鍒嗗壊鍚庣殑瀛楃涓叉暟缁�
     * @throws NotExpectedFormatedException
     */
    public static String[] splitWithLeastLength(String originalStr, String regex, int leastLength)
            throws NotExpectedFormatedException {
        if (StringUtil.isBlank(regex)) {
            return EmptyObjectConstant.EMPTY_STRING_ARRAY;
        }

        if (StringUtil.isBlank(originalStr)) {
            if (0 == leastLength)
                return EmptyObjectConstant.EMPTY_STRING_ARRAY;
            throw new NotExpectedFormatedException("鎸囧畾leastLength涓猴細" + leastLength + ", 浣嗘槸originalStr涓虹┖");
        }

        String[] strArray = originalStr.split(regex);
        if (leastLength > strArray.length) {
            throw new NotExpectedFormatedException("鎸囧畾leastLength涓猴細" + leastLength + ", 浣嗘槸originalStr涓�"
                    + originalStr);
        }
        return strArray;
    }

    /**
     * 鍒嗗壊瀛楃涓诧紝濡傛灉澶т簬鎸囧畾闀垮害锛�=leastLength ok锛夛紝鎶涘嚭寮傚父
     * @param originalStr
     *            鍘熷瓧绗︿覆
     * @param regex
     *            鍒嗗壊绗�
     * @param maxLength
     *            鎸囧畾鐨勬渶澶ax闀垮害
     * @return 鍒嗗壊鍚庣殑瀛楃涓叉暟缁�
     * @throws NotExpectedFormatedException
     */
    public static String[] splitWithMaxLength(String originalStr, String regex, int maxLength)
            throws NotExpectedFormatedException {
        if (StringUtil.isBlank(regex)) {
            return EmptyObjectConstant.EMPTY_STRING_ARRAY;
        }

        if (StringUtil.isBlank(originalStr)) {
            return EmptyObjectConstant.EMPTY_STRING_ARRAY;
        }

        String[] strArray = originalStr.split(regex);
        if (maxLength < strArray.length) {
            throw new NotExpectedFormatedException("鎸囧畾maxLength涓猴細" + maxLength + ", 浣嗘槸originalStr涓�" + originalStr);
        }
        return strArray;
    }

    /**
     * 鍒ゆ柇瀛楃涓叉槸鍚﹁秴杩囨寚瀹氶暱搴︼紝濡備綍瓒呰繃锛屾坊鍔犳寚瀹氬悗缂�
     * @param originalStr
     *            "閾舵椂鐨�
     * @param maxLength
     *            2
     * @param suffix
     *            ...
     * @return "閾舵椂..."
     */
    public static String subStringIfTooLong(String originalStr, int maxLength, String suffix) {
        if (StringUtil.isBlank(originalStr))
            return EMPTY_STRING;
        if (maxLength < 0)
            maxLength = 0;
        if (originalStr.length() > maxLength)
            return originalStr.substring(0, maxLength) + StringUtil.trimToEmpty(suffix);
        return originalStr;
    }

    /**
     * toString()鏂规硶<br>
     * Note:涓嶄細鍑虹幇npe锛屼笉浼氳繑鍥瀗ull
     * @param t
     * @return
     */
    public static <T> String toString(T t) {
        if (ObjectUtil.isBlank(t))
            return EMPTY_STRING;
        return t.toString();
    }

    /**
     * Returns a copy of the string, with leading and trailing whitespace
     * omitted. Don't worry the NullPointerException. Will never return Null.
     * @param originalStr
     * @return "" or String without empty str.
     */
    public static String trimToEmpty(String originalStr) {
        if (null == originalStr || originalStr.isEmpty())
            return EMPTY_STRING;
        if (originalStr.equals(BaseConstant.WORD_SEPARATOR))
            return originalStr;
        return originalStr.trim();
    }

    /**
     * URL编码
     * @param s
     *            String to be translated.
     * @param enc
     *            The name of a supported character encoding.
     * @return
     */
    public static String urlEncode(String s, String enc) {
        if (StringUtil.isBlank(s))
            return StringUtil.trimToEmpty(s);
        try {
            return java.net.URLEncoder.encode(trimToEmpty(s), enc);
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    /**
     * URL编码,使用UTF-8编码
     * @param s
     *            String to be translated.
     * @param enc
     *            The name of a supported character encoding.
     * @return
     */
    public static String urlEncode(String s) {
        if (StringUtil.isBlank(s))
            return StringUtil.trimToEmpty(s);
        return urlEncode(trimToEmpty(s), "UTF-8");
    }

    /**
     * 比较两个IP地址的大小
     * @param leftIP
     * @param rightIP
     * @return ">"(大于)；"<"(小于)；"="(等于)
     */
    public static String compareIP(String leftIP, String rightIP) {
        if (leftIP == null || rightIP == null) {
            return null;
        }

        if (leftIP.equalsIgnoreCase(rightIP)) {
            return "=";
        }

        String[] leftArray = leftIP.split("[.]");
        String[] rightArray = rightIP.split("[.]");

        if (Integer.parseInt(leftArray[0]) < Integer.parseInt(rightArray[0])) {
            return "<";
        } else if (Integer.parseInt(leftArray[0]) == Integer.parseInt(rightArray[0])) {
            if (Integer.parseInt(leftArray[1]) < Integer.parseInt(rightArray[1])) {
                return "<";
            } else if (Integer.parseInt(leftArray[1]) == Integer.parseInt(rightArray[1])) {
                if (Integer.parseInt(leftArray[2]) < Integer.parseInt(rightArray[2])) {
                    return "<";
                } else if (Integer.parseInt(leftArray[2]) == Integer.parseInt(rightArray[2])) {
                    if (Integer.parseInt(leftArray[3]) <= Integer.parseInt(rightArray[3])) {
                        return "<";
                    }
                }
            }
        }

        return ">";
    }

    /**
     * 十进制转三十六进制
     * @param source
     * @return
     */
    public static String decimaToThreehex(long source) {
        return Long.toString(source, 36).toUpperCase();
    }

    /**
     * 三十六进制转十进制
     * @param source
     * @return
     */
    public static long threehexToDecimal(String source) {
        return Long.parseLong(source, 36);
    }

    /**
     * 字符串加密
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    public static String convertTableName(String confTableName) {
        if (StringUtil.isBlank(confTableName))
            return null;
        confTableName = confTableName.toLowerCase();
        confTableName = confTableName.substring(0, 1).toUpperCase() + confTableName.substring(1);

        Pattern pattern = Pattern.compile("_[a-zA-Z]");
        Matcher matcher = pattern.matcher(confTableName);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String matchStr = matcher.group();
            matcher.appendReplacement(sb, matchStr.substring(matchStr.length() - 1).toUpperCase());
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}
