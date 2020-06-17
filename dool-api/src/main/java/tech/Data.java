package tech;


import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.List;

/**
 * @author zenglt
 * @date 2020/4/24 11:17
 */
public class Data {

    private byte[] datas;

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    public byte[] getDatas() {
        return this.datas;
    }

    public static void main(String[] args) {
        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
        Result result = ToAnalysis.parse(str); //封装的分词结果对象，包含一个terms列表
//		 System.out.println(result);
        List<Term> terms = result.getTerms(); //term列表，元素就是拆分出来的词以及词性
//		 System.out.println(terms);
        for(Term term:terms){
            System.out.println(term.getName()+"："+term.getNatureStr()+"："+term.getOffe());		//分词的内容 分词的词性 分词在原文本中的起始位置
        }
    }
}
