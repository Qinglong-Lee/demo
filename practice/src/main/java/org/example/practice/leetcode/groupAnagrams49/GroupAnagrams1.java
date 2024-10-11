package org.example.practice.leetcode.groupAnagrams49;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 对【异位词】分组
 * 异位词指字母顺序不同但每个字母出现的次数相同的单词
 *
 * 思路：【哈希表 + 符次数数组】
 *
 * 由于每个字符都对应一个字符编码，因此可以利用字符编码来构造哈希表的 key 以存储每个单词以及记录单词中每个字母出现的次数
 * 对单词中每个字符编码求和可以得到整个单词的【编码和】，作为 key，编码和相同的单词和一个 key 对应
 * 但是编码和并不一定唯一，因此需要手动解决冲突问题
 * 利用数组记录单词中每个字母出现的次数，当冲突发生，就利用数组对比，然后根据对比结果判断是否是【异位词】
 * 是则加到同一个 list 中，否则加入同一个 key 的另一个 list 中
 */
public class GroupAnagrams1 {
    int[] charCntArr = new int[26];

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>() {{
            new ArrayList<>();
        }};

        List<List<String>> rst;
        Map<Integer, List<List<String>>> listMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] strArr = str.toCharArray();
            int key = getKey(strArr);

            if (listMap.containsKey(key)) {
                List<List<String>> value = listMap.get(key);
                addToValue(value, str, strArr);
            } else {
                List<List<String>> value = new ArrayList<>() {{
                    add(new ArrayList<>() {{
                        add(str);
                    }});
                }};
                listMap.put(key, value);
            }
        }

        rst = listMap.values().stream().reduce((e1, e2) -> {
            for (List<String> list :
                    e2) {
                e1.add(list);
            }
            return e1;
        }).get();

        return rst;
    }

    private int getKey(char[] arr) {
        int rst = 0;
        for (int i = 0; i < arr.length; i++) {
            rst += arr[i];
        }
        return rst;
    }

    private void addToValue(List<List<String>> value, String str, char[] arr) {
        boolean NEW_STR = true;
        for (List<String> list :
                value) {
            char[] strArr = list.get(0).toCharArray();
            charCntArr = new int[26];

            for (int i = 0; i < strArr.length; i++) {
                int idx = strArr[i] - 'a';
                charCntArr[idx]++;
            }
            boolean DIFF = false;
            for (int i = 0; i < arr.length; i++) {
                int idx = arr[i] - 'a';
                if (charCntArr[idx] == 0) {
                    DIFF = true;
                    break;
                }
            }
            if (DIFF) continue;

            list.add(str);
            NEW_STR = false;
            break;
        }

        if (NEW_STR) {
            value.add(new ArrayList<>() {{
                add(str);
            }});
        }
    }

    public static void main(String[] args) {
//        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        String[] strs = new String[]{"compilations","bewailed","horology","lactated","blindsided","swoop","foretasted","ware","abuts","stepchild","arriving","magnet","vacating","relegates","scale","melodically","proprietresses","parties","ambiguities","bootblacks","shipbuilders","umping","belittling","lefty","foremost","bifocals","moorish","temblors","edited","hint","serenest","rendezvousing","schoolmate","fertilizers","daiquiri","starr","federate","rectal","case","kielbasas","monogamous","inflectional","zapata","permitted","concessions","easters","communique","angelica","shepherdess","jaundiced","breaks","raspy","harpooned","innocence","craters","cajun","pueblos","housetop","traits","bluejacket","pete","snots","wagging","tangling","cheesecakes","constructing","balanchine","paralyzed","aftereffects","dotingly","definitions","renovations","surfboards","lifework","knacking","apprises","minimalism","skyrocketed","artworks","instrumentals","eardrums","hunching","codification","vainglory","clarendon","peters","weeknight","statistics","ay","aureomycin","lorrie","compassed","speccing","galen","concerto","rocky","derision","exonerate","sultrier","mastoids","repackage","cyclical","gowns","regionalism","supplementary","bierce","darby","memorize","songster","biplane","calibrates","decriminalizes","shack","idleness","confessions","snippy","barometer","earthing","sequence","hastiness","emitted","superintends","stockades","busywork","dvina","aggravated","furbelow","hashish","overextended","foreordain","lie","insurance","recollected","interpreted","congregate","ranks","juts","dampen","gaits","eroticism","neighborhoods","perihelion","simulations","fumigating","balkiest","semite","epicure","heavier","masterpiece","bettering","lizzie","wail","batsmen","unbolt","cudgeling","bungalow","behalves","refurnishes","pram","spoonerisms","cornered","rises","encroachments","gabon","cultivation","parsed","takeovers","stampeded","persia","devotional","doorbells","psalms","cains","copulated","archetypal","cursores","inbred","paradigmatic","thesauri","rose","stopcocks","weakness","ballsier","jagiellon","torches","hover","conservationists","brightening","dotted","rodgers","mandalay","overjoying","supervision","gonads","portage","crap","capers","posy","collateral","funny","garvey","ravenously","arias","kirghiz","elton","gambolled","highboy","kneecaps","southey","etymology","overeager","numbers","ebullience","unseemly","airbrushes","excruciating","gemstones","juiciest","muftis","shadowing","organically","plume","guppy","obscurely","clinker","confederacies","unhurried","monastic","witty","breastbones","ijsselmeer","dublin","linnaeus","dervish","bluefish","selectric","syllable","pogroms","pacesetters","anastasia","pandora","foci","bipartisan","loomed","emits","gracious","warfare","uncouples","augusts","portray","refinery","resonances","expediters","deputations","indubitably","richly","motivational","gringo","hubris","mislay","scad","lambastes","reemerged","wart","zirconium","linus","moussorgsky","swopped","sufferer","sputtered","tamed","merrimack","conglomerate","blaspheme","overcompensate","rheas","pares","ranted","prisoning","rumor","gabbles","lummox","lactated","unzipping","tirelessly","backdate","puzzling","interject","rejections","bust","centered","oxymoron","tangibles","sejong","not","tameness","consumings","prostrated","rowdyism","ardent","macabre","rustics","dodoes","warheads","wraths","bournemouth","staffers","retold","stiflings","petrifaction","larkspurs","crunching","clanks","briefest","clinches","attaching","extinguished","ryder","shiny","antiqued","gags","assessments","simulated","dialed","confesses","livelongs","dimensions","lodgings","cormorants","canaries","spineless","widening","chappaquiddick","blurry","lassa","vilyui","desertions","trinket","teamed","bidets","mods","lessors","impressiveness","subjugated","rumpuses","swamies","annotations","batiks","ratliff","waxwork","grander","junta","chutney","exalted","yawl","joke","vocational","diabetic","bullying","edit","losing","banns","doleful","precision","excreting","foals","smarten","soliciting","disturbance","soggily","gabrielle","margret","faded","pane","jerusalem","bedpan","overtaxed","brigs","honors","repackage","croissants","kirov","crummier","limeades","grandson","criers","bring","jaundicing","omnibusses","gawking","tonsillectomies","deodorizer","nosedove","commence","faulkner","adultery","shakedown","wigwag","wiper","compatible","ultra","adamant","distillation","gestates","semi","inmate","onlookers","grudgingly","recipe","chaise","dialectal","aphids","flimsier","orgasm","sobs","swellheaded","utilize","karenina","irreparably","preteen","mumble","gingersnaps","alumnus","chummiest","snobbish","crawlspaces","inappropriate","ought","continence","hydrogenate","eskimo","desolated","oceanic","evasive","sake","laziest","tramps","joyridden","acclimatized","riffraff","thanklessly","harmonizing","guinevere","demanded","capabler","syphilitics","brainteaser","creamers","upholds","stiflings","walt","luau","deafen","concretely","unhand","animations","map","limbos","tranquil","windbreakers","limoges","varying","declensions","signs","green","snowbelt","homosexual","hopping","residue","ransacked","emeritus","pathologist","brazenly","forbiddingly","alfredo","glummest","deciphered","delusive","repentant","complainants","beets","syntactics","vicissitude","incompetents","concur","canaan","rowdies","streamer","martinets","shapeliness","videodiscs","restfulness","rhea","consumed","pooching","disenfranchisement","impoverishes","behalf","unsuccessfully","complicity","ulcerating","derisive","jephthah","clearing","reputation","kansan","sledgehammer","benchmarks","escutcheon","portfolios","mandolins","marketable","megalomaniacs","kinking","bombarding","wimple","perishes","rukeyser","squatter","coddle","traditionalists","sifts","agglomerations","seasonings","brightness","spices","claimant","sofas","ambulatories","bothered","businessmen","orly","kinetic","contracted","grenadiers","flooding","dissolved","corroboration","mussed","squareness","alabamans","dandelions","labyrinthine","pot","waxwing","residential","pizza","overjoying","whelps","overlaying","elanor","tented","masterminded","balsamed","powerhouses","tramps","eisenstein","voile","repellents","beaus","coordinated","wreckers","eternities","untwists","estrangements","vitreous","embodied"};
//        利用流式编程解决此题
//        Arrays.stream(strs).collect(Collectors.groupingBy(s -> Arrays.toString(s.codePoints().sorted().toArray()))).values();
//        System.out.println(Arrays.toString("compilations".codePoints().sorted().toArray()));

        GroupAnagrams1 test = new GroupAnagrams1();
//        System.out.println(test.groupAnagrams(strs).toString());
    }
}
