-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: recipe_system
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_categories`
--

DROP TABLE IF EXISTS `tb_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(225) NOT NULL,
  `category_img` varchar(225) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `modify_at` datetime DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categories`
--

LOCK TABLES `tb_categories` WRITE;
/*!40000 ALTER TABLE `tb_categories` DISABLE KEYS */;
INSERT INTO `tb_categories` VALUES (1,'鍋','http://localhost:8080/images/category/nabe.png',NULL,NULL,0),(2,'ビーガンフード','http://localhost:8080/images/category/chay.png',NULL,NULL,0),(3,'バーベキュー','http://localhost:8080/images/category/BBQ.png',NULL,NULL,0),(4,'ベトナム','http://localhost:8080/images/category/vn.png',NULL,NULL,0),(5,'和食','http://localhost:8080/images/category/washoku.png',NULL,NULL,0),(6,'洋食','http://localhost:8080/images/category/aumi.png',NULL,NULL,0),(7,'中華食','http://localhost:8080/images/category/chuuka.png',NULL,NULL,0),(8,'居酒屋','http://localhost:8080/images/category/izakaya.png',NULL,NULL,0);
/*!40000 ALTER TABLE `tb_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_comments`
--

DROP TABLE IF EXISTS `tb_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  `content` varchar(225) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modify_at` datetime DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `tb_comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`id`),
  CONSTRAINT `tb_comments_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `tb_posts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_comments`
--

LOCK TABLES `tb_comments` WRITE;
/*!40000 ALTER TABLE `tb_comments` DISABLE KEYS */;
INSERT INTO `tb_comments` VALUES (1,2,1,'作ってみます!',NULL,NULL,0),(2,2,2,'美味しそう',NULL,NULL,0),(3,2,3,'作ってみます!',NULL,NULL,0),(4,2,4,'Uầy ngon quá nhờ! phải làm thử ăn ngay mới được. Cảm ơn bạn đã chia sẻ cách làm nha!',NULL,NULL,0),(5,2,4,'Thử ngay thôi',NULL,NULL,0),(6,2,4,'nhìn ngon phết',NULL,NULL,0),(7,2,4,'thank kiu nha',NULL,NULL,0);
/*!40000 ALTER TABLE `tb_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_images`
--

DROP TABLE IF EXISTS `tb_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `image_url` varchar(225) NOT NULL,
  `caption` varchar(225) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `modify_at` datetime DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `tb_images_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `tb_posts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_images`
--

LOCK TABLES `tb_images` WRITE;
/*!40000 ALTER TABLE `tb_images` DISABLE KEYS */;
INSERT INTO `tb_images` VALUES (1,1,'http://localhost:8080/images/postlist/ramen.png','anh 1',NULL,NULL,0),(2,2,'http://localhost:8080/images/postlist/pho.png','anh 2',NULL,NULL,0),(3,3,'http://localhost:8080/images/postlist/pizaa.png','anh 3',NULL,NULL,0),(4,4,'http://localhost:8080/images/postlist/motsunabe.png','anh 4',NULL,NULL,0),(5,5,'http://localhost:8080/images/postlist/banhmi.png','anh 5',NULL,NULL,0),(6,6,'http://localhost:8080/images/postlist/nemran.png','anh 6',NULL,NULL,0);
/*!40000 ALTER TABLE `tb_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_post_categories`
--

DROP TABLE IF EXISTS `tb_post_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_post_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `tb_post_categories_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `tb_posts` (`id`),
  CONSTRAINT `tb_post_categories_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `tb_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_post_categories`
--

LOCK TABLES `tb_post_categories` WRITE;
/*!40000 ALTER TABLE `tb_post_categories` DISABLE KEYS */;
INSERT INTO `tb_post_categories` VALUES (3,1,5),(4,2,4),(5,3,6),(6,5,4),(7,6,4);
/*!40000 ALTER TABLE `tb_post_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_posts`
--

DROP TABLE IF EXISTS `tb_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `title` varchar(225) NOT NULL,
  `content` text NOT NULL,
  `like_number` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modify_at` datetime DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_posts`
--

LOCK TABLES `tb_posts` WRITE;
/*!40000 ALTER TABLE `tb_posts` DISABLE KEYS */;
INSERT INTO `tb_posts` VALUES (1,1,'らーめん','<p>ラーメンは、日本で有名で人気のある料理です。</p><p>これは、濃厚で風味豊かなスープと一緒に調理された麺の一種です。</p><p> 一般的に以下のような要素から構成されます：</p><div style=\"text-align: center;\"><img src=\"http://localhost:8080/images/postlist/ramen.png\" alt=\"Ramen\" style=\"max-width: 400px; height: auto;\"></div><h3>材料:</h3><ul><li>ラーメン麺：細い麺の一種</li><li>スープ：骨からとった出汁や大豆から作られることが一般的で、ニンニク、生姜、ねぎなどの香辛料が使われます</li><li>肉：豚肉、鶏肉、牛肉、または魚介類などの肉が使用されることがあります</li><li>野菜：キャベツ、もやし、ネギ、小松菜などの野菜が添えられることがあります</li><li>トッピング：海苔（のり）や味噌ダレなどのトッピングが使われます</li></ul><h3>ラーメンの作り方:</h3><ol><li>ラーメン麺を湯でゆでて、しっかりとした食感にする</li><li>スープを作る：骨からとった出汁や大豆から作るスープにニンニク、生姜、ねぎなどの香辛料を加える</li><li>肉をスープに加え、煮込んで肉を熟成させる</li><li>野菜とゆでた麺をスープに加え、ラーメンが風味を吸収するようにする</li><li>ラーメンを器に盛り付け、海苔や味噌ダレなどのトッピングを加える</li><li>熱々のラーメンを楽しむ</li></ol>',15,'2023-07-17 21:01:13',NULL,0),(2,1,'フォ','<div>Từ lâu, phở bò là món ăn truyền thống góp phần đưa Việt Nam tiến xa hơn trên bản đồ ẩm thực thế giới. Phở xuất phát từ dân gian, khởi đầu là những gánh phở rong phục vụ cho dân nghèo sau dần dần mới lên ngôi bán ở quán, cửa tiệm rồi đến nhà hàng. Ra đời tại vùng đồng bằng châu thổ sông Hồng rồi tiến vào Nam theo dòng người di cư. Ngày nay, không chỉ tại Việt Nam mà bạn cũng có thể thưởng thức phở bò tại nhiều quốc gia trên thế giới. Phở trở thành lựa chọn hàng đầu của người Việt cho bữa sáng thậm chí là bữa trưa, bữa tối… Để nấu phở bò ngon, chuẩn vị, bạn chỉ cần một số bí quyết và mẹo nhỏ là đã có thể thành công.</div><h2 style=\"padding: 10px 0 0 0;\">Sơ Chế Nguyên Liệu</h2><p>Hành tây bóc vỏ, 1 phần thái lát mỏng, 1 phần thái múi cau</p><p>Ngò rí rửa sạch, thái nhỏ.</p><p>Hành lá để riêng phần đầu hành, phần lá thái nhỏ</p><p>Các loại rau ăn kèm rửa sạch, để ráo.</p><h2 style=\"padding: 5px 0 0 0;\">Gói Gia Vị Nấu Phở</h2><p>Bạn cho quế khô, thảo quả, vỏ quýt, hoa hồi, đinh hương, hạt ngò vào túi vải sạch, buộc chặt lại.</p><h2 style=\"padding: 10px 0 0 0;\">Cách Nấu Nước Dùng Phở Bò</h2><p>Xương ống bò rửa sạch. Bắc nồi nước lên bếp cùng với một ít sả cây đun sôi. Cho xương vào chần sơ qua để khử đi mùi hôi.</p><p>Vớt xương đã chần ra, cho vào khay cùng với gừng, hành tím, hành tây cắt múi cau đem đi nướng đến khi xương vàng, lấy xương ra khỏi lò, cho ngay vào thau nước đá.</p><p>Đun sôi một nồi nước, cho xương bò đã nướng, gói gia vị vừa làm, hành tây, gừng đã nướng, sả vào nồi nước. Hầm xương khoảng 5 – 6 tiếng cho ra nước ngọt. Trong lúc hầm xương, bạn cho thịt bắp bò vào luộc chín. Lưu ý là luộc thịt bắp bò chín thì vớt ra ngay nếu để quá lâu thịt sẽ bị mềm nhũn. Sau đó vớt các nguyên liệu ra, lọc lấy nước dùng.</p><p>Bắc nồi nước dùng đã lọc lên bếp, nêm một ít muối, bột ngọt, hạt nêm, đường phèn sao cho vừa ăn, đun sôi và tắt bếp.</p><h2 style=\"padding: 10px 0 0 0;\">Trình Bày Và Thưởng Thức</h2><p>Thịt bò phi lê rửa sạch, thái lát mỏng.</p><p>Thịt bò bắp thái lát mỏng.</p><p>Chần giá và đầu hành qua nước sôi.</p><p>Chần bánh phở qua nước sôi.</p><p>Cho giá, đầu hành, bánh phở vào tô, xếp thịt bắp bò, thịt phi lê, hành lá, ngò rí, hành tây, ớt cắt lát rồi chan nước dùng vào.</p><p>Bạn thưởng thức phở cùng với các loại rau ăn kèm, tương ớt, tương đen.</p>',24,'2023-07-17 21:01:39',NULL,0),(3,1,'ピザ','<p style=\"padding: 20px 0;\">焼きたてのピザは格別なおいしさですよ。手づくりならお好みの具材でアレンジできるのもうれしいポイント。パーティーやおもてなしにもおすすめです。意外と簡単に作れるので、ぜひお試しくださいね。</p><h2 style=\"padding: 5px 0;\">材料(3枚分)</h2><h3 style=\"margin: 20px 0;\">生地</h3><ul style=\"list-style: none; padding: 0;\"><li style=\"padding: 5px 0;\">強力粉: 200g</li><li style=\"padding: 5px 0;\">薄力粉: 50g</li><li style=\"padding: 5px 0;\">砂糖: 20g</li><li style=\"padding: 5px 0;\">塩: 小さじ1/2</li><li style=\"padding: 5px 0;\">ドライイースト: 5g</li><li style=\"padding: 5px 0;\">水: 150ml</li><li style=\"padding: 5px 0;\">オリーブオイル: 20g</li><li style=\"padding: 5px 0;\">強力粉 (打ち粉): 適量</li></ul><p style=\"padding: 5px 0;\">ピザソース: 60g</p><h3 style=\"margin: 20px 0;\">具材</h3><ul style=\"list-style: none; padding: 0;\"><li style=\"padding: 5px 0;\">ソーセージ: 9本</li><li style=\"padding: 5px 0;\">ピーマン: 2個</li><li style=\"padding: 5px 0;\">ピザ用チーズ: 90g</li></ul><h2 style=\"padding: 5px 0;\">作り方</h2><ol style=\"padding-left: 20px;\"><li style=\"padding: 5px 0;\">ソーセージは5mm幅の斜め切りにしておきます。ピーマンはヘタと種を取り除き、薄切りにしておきます。</li><li style=\"padding: 5px 0;\">ボウルに塩以外の生地の材料を入れて混ぜ合わせます。</li><li style=\"padding: 5px 0;\">まとまってきたら塩を入れて捏ね、ひとまとめにします。打ち粉をした台に移し、滑らかになるまでさらに捏ねます。</li><li style=\"padding: 5px 0;\">生地を3等分にしてラップで包み、常温で15分ほど発酵させます。この間にオーブンを280℃に予熱します。</li><li style=\"padding: 5px 0;\">クッキングシートにラップを外した生地をのせます。みみになる縁の部分に厚みを残し、丸く伸ばします。</li><li style=\"padding: 5px 0;\">ピザソースを塗り、具材を順にのせます。</li><li style=\"padding: 5px 0;\">250℃のオーブンで10分ほど焼いたら完成です。</li></ol><h2 style=\"padding: 5px 0;\">料理のコツ・ポイント</h2><p style=\"padding: 5px 0;\">オリーブオイルは風味は変わりますが、サラダ油でもお作りいただけます。</p><p style=\"padding: 5px 0;\">具材は、お好みのものでアレンジしてお作りいただけます。</p>',21,'2023-07-17 21:01:48',NULL,0),(4,1,'もつ鍋','<p>スープまで美味しく味わえる博多名物のもつ鍋のご紹介です。ぷりぷりのもつがしゃきしゃきの野菜と良く合い、お箸が止まらなくなります！醤油ベースのスープの美味しさが口の中に広がります。</p><ol><li>もつはミックスを推奨しますが、もつ初挑戦なら白もつ(小腸)がオススメ。※ヌメリが気になる場合は軽く湯通ししてから使用。</li><li>鍋に【スープ】の材料を全て溶かし入れ、一口大に切ったもつを入れて中火にかけます。</li><li>火が通るとアクが出てくるので適当にすくい、アクが出なくなったら一旦火を止める。煮込み過ぎると旨み成分が溶け出るので注意。</li><li>約5cm幅の大きめザク切りにしたキャベツをドーナツ状に盛り、真ん中にもやしを山盛りで豪快に乗せます。</li><li>その上から約7cm幅に切ったニラを横一列に並べ、乾燥にんにくチップを適量散らし、白ごまと輪切り唐辛子をトッピング。</li><li>強火にかけてしばらくするとスープが増えてきます。キャベツがしんなりしてきたら食べ頃。中火に落としてお召し上がりください♪</li></ol>',4,'2023-07-17 21:02:27',NULL,0),(5,1,'バンミー','<p>Bánh mì là một trong món đồ ăn FastFood được rất nhiều người lựa chọn ăn sáng. Bởi nhân bánh thì đa dạng, hương vị lại rất thơm ngon, giàu dinh dưỡng và đặc biệt rất tiện lợi, nhanh gọn. Do vậy, có rất nhiều người nhận thấy đây chính là “mảnh đất vàng” đầy tiềm năng phát triển để kinh doanh. Tuy nhiên, cách làm bánh mì ngon để bán ăn sáng không phải cũng ai cũng biết và cũng làm được. Vì thế, ngay sau đây chúng tôi sẽ bật mí bạn công thức – cách làm bánh và một số mẹo giúp bạn làm bánh ngon hơn, hãy cùng theo dõi ngay nhé!</p><p style=\"margin-left: 5px;\">Hiện nay có rất nhiều loại bánh mì ngon để bạn chọn bán ăn sáng</p><p style=\"margin-left: 5px;\">Đi dọc bất cứ đường phố, ngõ xóm nào bạn cũng đều có dễ dàng bắt gặp các cửa hàng bánh mì khác nhau như bánh mì thịt nướng, bánh mì thịt nguội, bánh mì pate, bánh mì chả cá, bánh mì doner kebab, bánh mì kẹp trứng… Và trong bài viết này chúng tôi sẽ mách bạn cách làm bánh mì với 5 loại nhân khác nhau, hãy cùng theo dõi nhé</p><p style=\"margin-left: 5px;\">1/ Cách làm bánh mì thịt heo quay</p><p style=\"margin-left: 5px;\">Bánh mì heo quay chắc hẳn không còn xa lạ với nhiều người, đây là một trong những món bánh “phổ thông” gắn liền tuổi thơ của biết bao nhiều thế hệ. Vì thế, kinh doanh bán bánh mì heo quay để bán ăn sáng sẽ là môt ý tưởng rất hay đấy. Cách làm món bánh mì này rất đơn giản.</p><ul><li style=\"margin-left: 5px;\">Bánh mì: 4 ổ</li><li style=\"margin-left: 5px;\">Thịt heo: 300 gram</li><li style=\"margin-left: 5px;\">Rau răm: 1 mớ</li><li style=\"margin-left: 5px;\">Rượu trắng: 30ml</li><li style=\"margin-left: 5px;\">Nước lọc: 50ml</li><li style=\"margin-left: 5px;\">Ớt: 2 quả</li><li style=\"margin-left: 5px;\">Tỏi: 1 củ</li><li style=\"margin-left: 5px;\">Dưa leo: 1 quả</li><li style=\"margin-left: 5px;\">Gia vị: giấm (5ml), đường (2 thìa cà phê),bột ngũ vị hương (5 gram), tiêu xay (1/2 thìa cà phê), muối (1 thìa cà phê), nước mắm (50ml).</li></ul><p style=\"margin-left: 5px;\">Bánh mì thịt nướng heo quay thơm ngon giòn bì</p><p style=\"margin-left: 5px;\">Các bước tiến hành</p><p style=\"margin-left: 5px;\">Bước 1: Sơ chế nguyên liệu</p><p style=\"margin-left: 5px;\">Thịt heo mua về bạn rửa sạch thịt heo với nước muối loãng rồi lại tiếp tục rửa lại với nước sạch và vớt ra để ráo nước. Tiếp theo, khứa vài đường lên phần bì thịt heo để khi ướp gia vị thấm đều vào thịt.</p><p style=\"margin-left: 5px;\">Tỏi mua về bạn bóc hết lớp vỏ ngoài, sau đó bạn đem tỏi ớt rửa sạch đi rồi xay nhuyễn. Phần rau răm thì bạn bỏ rễ, lá hư đi rồi rửa sạch với nước 2 -3 lần để loại bỏ bụi bẩn, sạn nhé</p>',34,'2023-07-17 21:03:07',NULL,0),(6,1,'春巻き','<p>春巻きには、生の椎茸よりも、風味と食感の強い干し椎茸のほうが合うので、一晩くらい事前に水に浸けて戻しておきましょう。また、春雨も袋の表示時間通り、下茹でしてからざる上げしておきます（具材を炒める時にも軽く火が通るので、硬めに茹でるくらいでOK）。にんじんはせん切り、干し椎茸は軸を切り落として薄切りにします。生姜は用意できればくらいでいいので、あれば、みじん切りにします。たけのこの水煮も、にんじんと同じくらいの棒状に切ります。※たけのこは端から3～4㎜幅に切り、それを数枚重ねて棒状に切ります。ただ、真ん中の部分は節になっているため、細かくなりすぎないよう、節がばらばらになるくらいでストップするようにしましょう（たけのこの大きさにもよるので参考まで）フライパンにごま油小さじ1を入れて中火で熱し、生姜とにんじんを入れて1分ほど炒めます。続けて、たけのこと鶏ひき肉を加え、箸でひき肉をほぐしながら、手早く炒め合わせます。鶏肉の色が変わってきたら、塩ひとつまみ、こしょう少々（分量外）で下味をつけます（生姜を入れてない場合はこしょう多めで！）水気を切った春雨と干し椎茸を加え、春雨に残った水分を飛ばすくらいの気持ちで1分ほど炒めてから火を止めましょう。Aの調味料（醤油小さじ2、酒大さじ1、砂糖小さじ1/2、椎茸の戻し汁小さじ4、塩小さじ1/5）を加え、弱めの中火にかけて沸くのを待ちます。とろみ付けのためのBの片栗粉小さじ1、水大さじ1も合わせておきます。また、フライパンの中の底にある煮汁が沸いたら、箸でざっと（おおよそでよいので）春雨だけを集めて奥に移動させます。</p>',56,'2023-07-17 21:05:16',NULL,0);
/*!40000 ALTER TABLE `tb_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_save_posts`
--

DROP TABLE IF EXISTS `tb_save_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_save_posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `modify_at` datetime DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `tb_save_posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`id`),
  CONSTRAINT `tb_save_posts_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `tb_posts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_save_posts`
--

LOCK TABLES `tb_save_posts` WRITE;
/*!40000 ALTER TABLE `tb_save_posts` DISABLE KEYS */;
INSERT INTO `tb_save_posts` VALUES (1,1,1,NULL,NULL,0),(2,1,2,NULL,NULL,0),(3,1,3,NULL,NULL,0);
/*!40000 ALTER TABLE `tb_save_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sessions`
--

DROP TABLE IF EXISTS `tb_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `session_id` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `expire_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `session_id_UNIQUE` (`session_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_sessions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sessions`
--

LOCK TABLES `tb_sessions` WRITE;
/*!40000 ALTER TABLE `tb_sessions` DISABLE KEYS */;
INSERT INTO `tb_sessions` VALUES (1,'1689596387781',1,'2023-07-17 21:19:48','2023-07-18 21:19:48'),(2,'1690061973099',1,'2023-07-23 06:39:33','2023-07-24 06:39:33'),(3,'1690112993761',1,'2023-07-23 20:49:54','2023-07-24 20:49:54'),(4,'1690113437821',1,'2023-07-23 20:57:18','2023-07-24 20:57:18'),(5,'1690114368742',1,'2023-07-23 21:12:49','2023-07-24 21:12:49'),(6,'1690115677920',1,'2023-07-23 21:34:38','2023-07-24 21:34:38'),(7,'1690115755216',1,'2023-07-23 21:35:55','2023-07-24 21:35:55'),(8,'1690217934541',1,'2023-07-25 01:58:55','2023-07-26 01:58:55'),(9,'$2a$10$OKKW0KZPkNrcevAm1SbVUuZyjHsj1hA59Bkqs2ZpuaJLg5r8gLbHa',3,'2023-07-30 10:51:27','2023-07-31 10:51:27');
/*!40000 ALTER TABLE `tb_sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_shares`
--

DROP TABLE IF EXISTS `tb_shares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_shares` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modify_at` datetime DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `tb_shares_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`id`),
  CONSTRAINT `tb_shares_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `tb_posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shares`
--

LOCK TABLES `tb_shares` WRITE;
/*!40000 ALTER TABLE `tb_shares` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_shares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users`
--

DROP TABLE IF EXISTS `tb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(225) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL DEFAULT 'USER',
  `phone_number` varchar(11) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` enum('FEMALE','MALE','OTHER') DEFAULT NULL,
  `lnk_avatar` varchar(225) DEFAULT NULL,
  `verification_code` varchar(50) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modify_at` datetime DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
INSERT INTO `tb_users` VALUES (1,NULL,'$2a$10$b58dpwqXGSlXZjvCQT7e0OY2Sx45eFE3sKOktaHLMl5Czk9Q.vCCu','huepham425@gmail.com','USER',NULL,NULL,NULL,NULL,NULL,NULL,'2023-07-17 21:01:06',NULL,0),(2,NULL,'$2a$10$vLoSpV0Jq.6fdTBuJu.yOey0hU2079Hds8oZTv.7hhPB4Uoh9ZHWC','huepham426@gmail.com','USER',NULL,NULL,NULL,NULL,NULL,NULL,'2023-07-23 06:39:53',NULL,0),(3,NULL,'$2a$10$MDDMONaXRyZEhyLLB9hU8eYpJqidGqY6YlpWx01/hVtkuJRQnK5qG','huepham424@gmail.com','USER',NULL,NULL,NULL,NULL,NULL,NULL,'2023-07-29 15:46:18',NULL,0);
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-30 12:01:18
