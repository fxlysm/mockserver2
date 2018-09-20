package cn.imovie.mockserver.taopiaopiao.util;

public class filmCodeUtil {

    public static String Getcountries(String code){
/**
 * 001 中国 002 香港 003 台湾 011 朝鲜 012 日本 013 越南 014 泰国 015 缅甸 016 新加坡 017 印尼 018 斯里兰卡 019 印度 020 巴基斯坦 021 蒙古 022 伊拉克 023 黎巴嫩 024 土尔其 025 菲律宾 026 尼泊尔
 * 027 叙利亚 035 澳大利亚 036 新西兰 041 埃及 042 阿尔及利亚 043 突尼斯 044 摩洛哥 05l 美国 052 墨西哥 053 委内瑞拉 054 古巴 055 哥伦比亚 056 玻利维亚 058 阿根廷 059 加拿大 060 巴西 073 丹麦
 * 076 荷兰 077 西班牙 078 意大利 079 西德 080 东德 081 瑞士 082 奥地利 083 波兰 084 捷克 085 匈牙利
 * 086 罗马尼亚 087 保加利亚 088 南斯拉夫 089 阿尔巴尼亚 090 希腊 091 俄罗斯 092 比利时 093 瑞典
 */
        if(code.equals("001")){
            return "中国";
        }else if(code.equals("002")){
            return "香港";
        }else if(code.equals("003")){
            return "台湾";
        }else if(code.equals("004")){
            return "澳门";
        }else if(code.equals("005")){
            return "阿富汗";
        }else if(code.equals("006")){
            return "阿联酋";
        }else if(code.equals("007")){
            return "阿曼";
        }else if(code.equals("010")){
            return "韩国";
        }else if(code.equals("011")){
            return "朝鲜";
        }else if(code.equals("012")){
            return "日本";
        }else if(code.equals("051")){
            return "美国";
        }else if(code.equals("072")){
            return "挪威";
        }else if(code.equals("073")){
            return "丹麦";
        }else if(code.equals("074")){
            return "英国";
        }else if(code.equals("075")){
            return "法国";
        }else if(code.equals("076")){
            return "荷兰";
        }else if(code.equals("077")){
            return "西班牙";
        }else {
            return "未知";
        }
    }


    public static String GetfilmType(String code){
        if(code.equals("0")){
            return "观摩影片";
        }else if(code.equals("1")){
            return "普通（数字）";
        }else if(code.equals("2")){
            return "普通（立体）";
        }else if(code.equals("3")){
            return "巨幕";
        }else if(code.equals("4")){
            return "巨幕立体";
        }else if(code.equals("5")){
            return "胶片（进口）";
        }else if(code.equals("6")){
            return "其它特种电影";
        }else if(code.equals("7")){
            return "16mm胶片";
        }else if(code.equals("8")){
            return "其它";
        }else if(code.equals("a")){
            return "动画片";
        }else if(code.equals("b")){
            return "记录片";
        }else if(code.equals("c")){
            return "科教片";
        }else {
            return "未知";
        }
    }



    public static String Getfilmversion(String code){
        if(code.equals("1")){
            return "2D";
        }else if(code.equals("2")){
            return "3D";
        }else if(code.equals("4")){
            return "IMAX 3D";
        }else if(code.equals("3")){
            return "IMAX 2D";
        }else if(code.equals("5")){
            return "中国巨幕 2D";
        }else if(code.equals("5")){
            return "中国巨幕 2D";
        }else if(code.equals("9")){
            return "中国巨幕 3D";
        }else {
            return "未知";
        }
    }
}
