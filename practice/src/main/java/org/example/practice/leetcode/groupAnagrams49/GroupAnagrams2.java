package org.example.practice.leetcode.groupAnagrams49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对【异位词】分组
 * 异位词指字母顺序不同但每个字母出现的次数相同的单词
 *
 * 思路：【哈希表 + 字符次数数组】
 * 本题的关键点事：如何将从不同单词字符串得到【唯一 key】
 *
 * 由于每个字符都对应一个字符编码，因此可以利用字符编码来构造哈希表的 key 以存储每个单词以及记录单词中每个字母出现的次数
 * 利用数组记录单词中每个字母出现的次数，然后根据【次数数组】拼接一个【出现次数 key】：将每个字符和其出现次数拼接成串，这样就得到唯一 key
 * 每次将 key 相同的单词加入 key 对应的 list 即可
 */
public class GroupAnagrams2 {
    private int[] charCntArr;

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>() {{
            new ArrayList<>();
        }};

        List<List<String>> rst;
        Map<String, List<String>> listMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];

            buildCharCntArr(str);
            String key = getUniqueKey(str);

            List<String> list = listMap.getOrDefault(key, new ArrayList<>());
            list.add(str);
            listMap.put(key, list);
        }

        rst = listMap.values().stream().toList();

        return rst;
    }

    private String getUniqueKey(String str) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < charCntArr.length; j++) {
            char c = (char)('a' + j);
            int charCnt = charCntArr[c - 'a'];

            if (charCnt > 0) {
                sb.append(c).append(charCnt);
            }
        }

        return sb.toString();
    }

    private void buildCharCntArr(String str) {
        charCntArr = new int[26];
        for (int j = 0; j < str.length(); j++) {
            int idx = str.charAt(j) - 'a';
            charCntArr[idx]++;
        }
    }

    public static void main(String[] args) {
//        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat","ac","bd","aac","bbd","aacc","bbdd","acc","bdd"};
//        String[] strs = new String[]{"compilations","bewailed","horology","lactated","blindsided","swoop","foretasted","ware","abuts","stepchild","arriving","magnet","vacating","relegates","scale","melodically","proprietresses","parties","ambiguities","bootblacks","shipbuilders","umping","belittling","lefty","foremost","bifocals","moorish","temblors","edited","hint","serenest","rendezvousing","schoolmate","fertilizers","daiquiri","starr","federate","rectal","case","kielbasas","monogamous","inflectional","zapata","permitted","concessions","easters","communique","angelica","shepherdess","jaundiced","breaks","raspy","harpooned","innocence","craters","cajun","pueblos","housetop","traits","bluejacket","pete","snots","wagging","tangling","cheesecakes","constructing","balanchine","paralyzed","aftereffects","dotingly","definitions","renovations","surfboards","lifework","knacking","apprises","minimalism","skyrocketed","artworks","instrumentals","eardrums","hunching","codification","vainglory","clarendon","peters","weeknight","statistics","ay","aureomycin","lorrie","compassed","speccing","galen","concerto","rocky","derision","exonerate","sultrier","mastoids","repackage","cyclical","gowns","regionalism","supplementary","bierce","darby","memorize","songster","biplane","calibrates","decriminalizes","shack","idleness","confessions","snippy","barometer","earthing","sequence","hastiness","emitted","superintends","stockades","busywork","dvina","aggravated","furbelow","hashish","overextended","foreordain","lie","insurance","recollected","interpreted","congregate","ranks","juts","dampen","gaits","eroticism","neighborhoods","perihelion","simulations","fumigating","balkiest","semite","epicure","heavier","masterpiece","bettering","lizzie","wail","batsmen","unbolt","cudgeling","bungalow","behalves","refurnishes","pram","spoonerisms","cornered","rises","encroachments","gabon","cultivation","parsed","takeovers","stampeded","persia","devotional","doorbells","psalms","cains","copulated","archetypal","cursores","inbred","paradigmatic","thesauri","rose","stopcocks","weakness","ballsier","jagiellon","torches","hover","conservationists","brightening","dotted","rodgers","mandalay","overjoying","supervision","gonads","portage","crap","capers","posy","collateral","funny","garvey","ravenously","arias","kirghiz","elton","gambolled","highboy","kneecaps","southey","etymology","overeager","numbers","ebullience","unseemly","airbrushes","excruciating","gemstones","juiciest","muftis","shadowing","organically","plume","guppy","obscurely","clinker","confederacies","unhurried","monastic","witty","breastbones","ijsselmeer","dublin","linnaeus","dervish","bluefish","selectric","syllable","pogroms","pacesetters","anastasia","pandora","foci","bipartisan","loomed","emits","gracious","warfare","uncouples","augusts","portray","refinery","resonances","expediters","deputations","indubitably","richly","motivational","gringo","hubris","mislay","scad","lambastes","reemerged","wart","zirconium","linus","moussorgsky","swopped","sufferer","sputtered","tamed","merrimack","conglomerate","blaspheme","overcompensate","rheas","pares","ranted","prisoning","rumor","gabbles","lummox","lactated","unzipping","tirelessly","backdate","puzzling","interject","rejections","bust","centered","oxymoron","tangibles","sejong","not","tameness","consumings","prostrated","rowdyism","ardent","macabre","rustics","dodoes","warheads","wraths","bournemouth","staffers","retold","stiflings","petrifaction","larkspurs","crunching","clanks","briefest","clinches","attaching","extinguished","ryder","shiny","antiqued","gags","assessments","simulated","dialed","confesses","livelongs","dimensions","lodgings","cormorants","canaries","spineless","widening","chappaquiddick","blurry","lassa","vilyui","desertions","trinket","teamed","bidets","mods","lessors","impressiveness","subjugated","rumpuses","swamies","annotations","batiks","ratliff","waxwork","grander","junta","chutney","exalted","yawl","joke","vocational","diabetic","bullying","edit","losing","banns","doleful","precision","excreting","foals","smarten","soliciting","disturbance","soggily","gabrielle","margret","faded","pane","jerusalem","bedpan","overtaxed","brigs","honors","repackage","croissants","kirov","crummier","limeades","grandson","criers","bring","jaundicing","omnibusses","gawking","tonsillectomies","deodorizer","nosedove","commence","faulkner","adultery","shakedown","wigwag","wiper","compatible","ultra","adamant","distillation","gestates","semi","inmate","onlookers","grudgingly","recipe","chaise","dialectal","aphids","flimsier","orgasm","sobs","swellheaded","utilize","karenina","irreparably","preteen","mumble","gingersnaps","alumnus","chummiest","snobbish","crawlspaces","inappropriate","ought","continence","hydrogenate","eskimo","desolated","oceanic","evasive","sake","laziest","tramps","joyridden","acclimatized","riffraff","thanklessly","harmonizing","guinevere","demanded","capabler","syphilitics","brainteaser","creamers","upholds","stiflings","walt","luau","deafen","concretely","unhand","animations","map","limbos","tranquil","windbreakers","limoges","varying","declensions","signs","green","snowbelt","homosexual","hopping","residue","ransacked","emeritus","pathologist","brazenly","forbiddingly","alfredo","glummest","deciphered","delusive","repentant","complainants","beets","syntactics","vicissitude","incompetents","concur","canaan","rowdies","streamer","martinets","shapeliness","videodiscs","restfulness","rhea","consumed","pooching","disenfranchisement","impoverishes","behalf","unsuccessfully","complicity","ulcerating","derisive","jephthah","clearing","reputation","kansan","sledgehammer","benchmarks","escutcheon","portfolios","mandolins","marketable","megalomaniacs","kinking","bombarding","wimple","perishes","rukeyser","squatter","coddle","traditionalists","sifts","agglomerations","seasonings","brightness","spices","claimant","sofas","ambulatories","bothered","businessmen","orly","kinetic","contracted","grenadiers","flooding","dissolved","corroboration","mussed","squareness","alabamans","dandelions","labyrinthine","pot","waxwing","residential","pizza","overjoying","whelps","overlaying","elanor","tented","masterminded","balsamed","powerhouses","tramps","eisenstein","voile","repellents","beaus","coordinated","wreckers","eternities","untwists","estrangements","vitreous","embodied"};

//        Arrays.stream(strs).collect(Collectors.groupingBy(s -> Arrays.toString(s.codePoints().sorted().toArray()))).values();

        GroupAnagrams2 test = new GroupAnagrams2();
        System.out.println(test.groupAnagrams(strs).toString());
    }
}
