package utils;

import org.jetbrains.annotations.NotNull;

public class UploadUtils {
    /**
     * 获取上传文件随机名称
     *
     * @param realName
     * @return
     */
    public static String getUUIDName(@NotNull String realName) {
        int index = realName.lastIndexOf(".");
        if (index == -1) {
            return UUIDUtils.getId();
        } else {
            return UUIDUtils.getId() + realName.substring(index);
        }
    }

    /**
     * 获取文件真实名称
     *
     * @param text
     * @return
     */
    public static String getRealName(@NotNull String text) {
        int index = text.lastIndexOf("\\");
        return text.substring(index + 1);
    }

    public static String getDir(@NotNull String name) {
        int hc = name.hashCode();
        String hex = Integer.toHexString(hc);
        int hl = hex.length();
        for (int i = 0; i < 8 - hl; i++) {
            hex = "0" + hex;
        }
        return "/" + hex.charAt(0) + "/" + hex.charAt(1);
    }
}
