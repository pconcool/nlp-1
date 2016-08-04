package com.xb.utils.pinyin.util;

import com.xb.constant.Constant;
import com.xb.constant.FileConstant;
import com.xb.utils.FileNIOUtil;
import com.xb.utils.TextUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2016/5/19.
 * 从文章中提取句子，放到sentence.txt中
 */
public class ArticleProcess {

    public static void genSentence() {
        List<String> fileList = FileNIOUtil.dirList(FileConstant.ARTICLE_DIR);
        for (String file : fileList) {
            String content = FileNIOUtil.readFile(file, Constant.CHARSET_UTF8);

            List<String> list = extractChineseSentences(content);

            for (String str : list) {
                if (StringUtils.isNotBlank(str) && str.length() > 2) {
                    FileNIOUtil.writeFile(FileConstant.ARTICLE_DIR_SENTENCE, str,
                            Constant.CHARSET_UTF8);
                }
            }
        }
    }

    public static List<String> extractChineseSentences(String content) {
        content = content.replaceAll(" ", "");
        content = content.replaceAll("\\\\r", "");
        content = content.replaceAll("\\\\n", "");
        content = content.replaceAll("\\\\t", "");

        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < content.length(); i++) {
            Character c = Character.valueOf(content.charAt(i));
            if (TextUtils.isChineseChar(c)) {
                sb.append(c);
            } else {
                list.add(sb.toString() + "\n");
                sb.delete(0, sb.length());
            }
        }

        return list;
    }

    public static void main(String[] args) {
        genSentence();
    }
}
