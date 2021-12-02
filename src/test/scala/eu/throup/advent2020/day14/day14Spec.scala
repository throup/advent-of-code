package eu.throup.advent2020.day14

import org.scalatest.freespec.AnyFreeSpec

class day14Spec extends AnyFreeSpec {
  "day 14 - part 1" - {
    "define the api" - {
      "takes a string as input" in {
        part1(exampleInput)
      }
      "returns an Long as output" in {
        val output: Long = part1(exampleInput)
      }
    }

    "examples" - {
      "Instruction example" in {
        val input = exampleInput
        val output = part1(input)

        assert(output == 165)
      }

      "Task set" in {
        val input = challengeInput
        val output = part1(input)

        assert(output == 11884151942312L)
      }
    }
  }

  "day 14 - part 2" - {
    "define the api" - {
      "takes a string as input" in {
        part2(exampleInput2)
      }
      "returns an Int as output" in {
        val output: Long = part2(exampleInput2)
      }
    }

    "examples" - {
//      "Instruction example 1" in {
//        val input = exampleInput
//        val output = part2(input)
//
//        assert(output == 208)
//      }

      "Instruction example 2" in {
        val input = exampleInput2
        val output = part2(input)

        assert(output == 208)
      }

      "Task set" in {
        val input = challengeInput
        val output = part2(input)

        assert(output == 2625449018811L)
      }
    }
  }

  "Bitmask" - {
    "with a single 1 can mask that value" - {
      val mask = Bitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX1")
      "mask of 0 is 1" in assert(mask.it(0) == 1)
      "mask of 1 is 1" in assert(mask.it(1) == 1)
      "mask of 2 is 3" in assert(mask.it(2) == 3)
      "mask of 3 is 3" in assert(mask.it(3) == 3)
      "mask of 34359738368 is 34359738369" in assert(mask.it(34359738368L) == 34359738369L)
      "mask of 34359738369 is 34359738369" in assert(mask.it(34359738369L) == 34359738369L)
    }
    "with a single 0 can mask that value" - {
      val mask = Bitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX0")
      "mask of 0 is 0" in assert(mask.it(0) == 0)
      "mask of 1 is 0" in assert(mask.it(1) == 0)
      "mask of 2 is 2" in assert(mask.it(2) == 2)
      "mask of 3 is 2" in assert(mask.it(3) == 2)
      "mask of 34359738368 is 34359738368" in assert(mask.it(34359738368L) == 34359738368L)
      "mask of 34359738369 is 34359738368" in assert(mask.it(34359738369L) == 34359738368L)
    }
  }

  "Bitmask2" - {
    "with mask" - {
      val mask = Bitmask2("000000000000000000000000000000X1001X")
      "mask of 42" in {
        val output: Set[Long] = mask.it(42)
        assert(output == Set(26, 27, 58, 59))
      }
    }
  }

  val exampleInput = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\nmem[8] = 11\nmem[7] = 101\nmem[8] = 0"

  val exampleInput2 = "mask = 000000000000000000000000000000X1001X\nmem[42] = 100\nmask = 00000000000000000000000000000000X0XX\nmem[26] = 1"

  val challengeInput = "mask = 0010X01001X010000110100000X000010X11\nmem[41717] = 288\nmem[54146] = 1656\nmem[30135] = 4799584\nmask = 01X10101X11X01XX01X000011X1000110110\nmem[29142] = 13227025\nmem[32455] = 1814\nmem[42522] = 385316684\nmem[29904] = 5334\nmem[51087] = 1492\nmask = 01110X1X1XX0100011X0111X11001X1X1001\nmem[16001] = 2818333\nmem[896] = 152318161\nmem[7380] = 1741200\nmem[36363] = 3994854\nmem[51852] = 5003\nmem[31197] = 8946048\nmem[38377] = 699057\nmask = 00XX0X11111001001XX100X1010X00X10X01\nmem[41981] = 8217309\nmem[929] = 5355\nmem[21551] = 1130885\nmask = 111X01X111001X0XX11X110100X1101100XX\nmem[38540] = 877233\nmem[4594] = 156077284\nmem[17004] = 12673792\nmem[11717] = 12697488\nmem[58553] = 890949\nmask = 001101X101X1100001X0000X0X100001110X\nmem[49613] = 14831731\nmem[18410] = 386265\nmem[11204] = 66992\nmem[7462] = 330749684\nmem[48489] = 186227974\nmem[49880] = 13957\nmem[37574] = 101061\nmask = 0X11010101111000011XX00001X0101111X0\nmem[43270] = 63321063\nmem[8868] = 8357760\nmem[51871] = 7561\nmem[59958] = 76822813\nmem[13346] = 298124646\nmem[28205] = 1479\nmem[20102] = 898175404\nmask = 00X00X11111001001X1X0000011010000101\nmem[39594] = 471\nmem[23863] = 472\nmem[45424] = 881\nmem[5520] = 68851082\nmask = 1111011X1XX01XX01110010X011010X01100\nmem[7932] = 7104\nmem[50570] = 521355116\nmem[33698] = 77366694\nmem[54041] = 1967898\nmem[1940] = 272765\nmem[42608] = 557758\nmem[48653] = 95074\nmask = 01110101011110001100100101X11000XX0X\nmem[5032] = 324136\nmem[19968] = 25\nmem[37008] = 370\nmem[51927] = 7820968\nmem[3960] = 273343\nmem[26318] = 9940851\nmask = 11X1011X1X00111XX110X000011X10010001\nmem[49437] = 13322545\nmem[46843] = 690911\nmem[58945] = 13770143\nmem[4847] = 67337\nmem[6128] = 371\nmask = 1100X1X1X10011X01X100011110011110001\nmem[6466] = 926046\nmem[20503] = 8735222\nmem[29727] = 18641\nmem[20030] = 1090613\nmem[54347] = 33354545\nmask = 11XX01X111X011001110X0110101100XX011\nmem[59773] = 1775\nmem[31974] = 722\nmem[15867] = 46611\nmem[37151] = 3019104\nmem[21915] = 96864303\nmem[9222] = 10601111\nmem[20777] = 1235555\nmask = 0111010001111X00110X0X1011X0X1100000\nmem[46581] = 20573\nmem[32355] = 58869216\nmem[45378] = 13272459\nmem[22414] = 322109637\nmem[34083] = 20766732\nmem[63698] = 3924531\nmask = 010101011X100X01011001X10X101X11011X\nmem[57710] = 113212\nmem[31202] = 231541678\nmem[51278] = 219614\nmem[63698] = 1042138\nmem[15026] = 7000054\nmem[51217] = 500505\nmask = 0X100001000X0100XX1XX1101101001X1000\nmem[7011] = 521173\nmem[13238] = 157620\nmem[30900] = 19373\nmem[27810] = 163020969\nmem[54982] = 96357825\nmem[22824] = 408599\nmem[12477] = 48103186\nmask = 00000010111XX00XX110X010000000010111\nmem[54146] = 17970\nmem[41376] = 1503\nmem[31900] = 438330985\nmem[13355] = 435056\nmem[64758] = 1052852148\nmem[59142] = 1950135\nmem[6781] = 13502\nmask = 01X10X0111110X00X0XX010100X10X1X0101\nmem[51107] = 13121312\nmem[20134] = 243\nmem[56802] = 24720\nmask = 1111010111101100XX101111000000010X1X\nmem[56384] = 729\nmem[32508] = 258811315\nmem[39920] = 252650\nmem[54657] = 119337\nmem[43559] = 1614394\nmem[43973] = 36253\nmask = 00100XXXXX1X10000110110100010001XX01\nmem[33796] = 171340858\nmem[62392] = 2042140\nmem[21146] = 202316\nmem[50189] = 17797984\nmem[59958] = 3871673\nmem[46838] = 7418\nmask = X0100X0X111X11011110X0000001001X1111\nmem[35424] = 14950493\nmem[64240] = 40746\nmem[22319] = 149408\nmem[32929] = 57764\nmem[4571] = 138473\nmem[13202] = 6496022\nmem[59210] = 24648253\nmask = 00XX0001111011111110X1X0111010XX0X10\nmem[31028] = 3706\nmem[64362] = 57739\nmem[22920] = 491669\nmask = 011X0101X111XX000110XX01X110X001110X\nmem[22272] = 22414\nmem[59958] = 10131\nmem[9299] = 59050364\nmem[54292] = 185609950\nmem[11363] = 679\nmem[55554] = 127124\nmask = 11110X0110X010101X10010X011010011011\nmem[13140] = 23895\nmem[54725] = 364383\nmem[12768] = 29526\nmem[21134] = 31494\nmem[32345] = 13843\nmem[38437] = 576352606\nmem[35535] = 1948\nmask = X111011111X100X001111111000X111000X0\nmem[34738] = 395933\nmem[33438] = 644\nmem[34480] = 56137\nmem[33415] = 71\nmem[55820] = 40580559\nmem[37978] = 104370\nmem[60594] = 47366315\nmask = 011X0X110110X000100X0X1110X00000XX00\nmem[2065] = 5611\nmem[42368] = 139274892\nmem[11145] = 48456\nmem[11668] = 5352867\nmem[40280] = 864639\nmask = 1XX1X00X11010X10011111XXX1101100111X\nmem[39384] = 12733115\nmem[48860] = 24406\nmem[46230] = 13931503\nmask = 00X001100X11100001101100001X1XX10001\nmem[11204] = 11902\nmem[42496] = 95479\nmem[58594] = 120902\nmem[62260] = 43802019\nmem[41882] = 889\nmask = 00X000X011111X0001100000000000X00001\nmem[54549] = 73578351\nmem[15308] = 225676\nmask = 11110X011100100X11111111XX0101100111\nmem[62902] = 115296396\nmem[163] = 47992\nmem[27595] = 1080625\nmask = X01101X1111XX1X0111X0101000000111010\nmem[45311] = 950513\nmem[35853] = 3048594\nmem[25353] = 42914232\nmem[4595] = 165637540\nmem[5173] = 4666\nmem[27595] = 33115384\nmask = 11XX0101X10010000111X111001000X00111\nmem[5313] = 16770\nmem[21570] = 21589452\nmem[45537] = 25984\nmem[7425] = 1016\nmem[57579] = 4400441\nmem[16371] = 27936\nmem[29544] = 699\nmask = 0011010X1X1010X111X10X0001010XX01001\nmem[12546] = 66366447\nmem[4594] = 660822686\nmem[61425] = 3035\nmem[16121] = 27102624\nmem[1316] = 1781\nmem[23623] = 572865256\nmask = 0111X1011XX01X0101100100X110000X001X\nmem[23341] = 6402065\nmem[52500] = 1977612\nmask = 00X0001011X1100001101100X01100X00111\nmem[64017] = 31705\nmem[35512] = 1445\nmem[35781] = 5233653\nmem[44050] = 40\nmask = 01110100X11110X0X1000X11111X0X1X000X\nmem[12867] = 190010\nmem[36252] = 747672\nmem[23167] = 204357\nmem[27385] = 12016\nmem[47464] = 784707845\nmem[48043] = 627253\nmask = 0111X0X0X100100X1110011X11000X000100\nmem[37676] = 36371\nmem[59916] = 3315\nmem[29136] = 15854324\nmem[37249] = 132828\nmem[53802] = 79432\nmask = X11X1101101X110X0X10X1000011000XXX00\nmem[17461] = 8158\nmem[33712] = 1029431\nmem[1042] = 837675\nmask = 00110X011010100X1101010100010XX11011\nmem[16121] = 853733\nmem[18346] = 5303\nmem[60174] = 506801115\nmem[1817] = 1868\nmem[46166] = 168\nmem[14602] = 1968\nmem[5368] = 109371\nmask = X1110XX111X100X0011X101111001101XX10\nmem[21630] = 8667\nmem[55820] = 200651\nmem[11204] = 34034\nmask = 1111011X10101X001110001X01XX00010101\nmem[62846] = 105848\nmem[43437] = 28568\nmem[16213] = 724626106\nmem[32677] = 149932\nmem[23580] = 573\nmem[22272] = 3393406\nmem[64633] = 4838\nmask = 011001010XX1X10001100X00X0010001X0X0\nmem[23580] = 992\nmem[7692] = 1236\nmem[33389] = 560975\nmem[7177] = 5868\nmask = X1X10101X0101X0011101100111110100101\nmem[99] = 6843772\nmem[30553] = 554826032\nmem[61798] = 5939100\nmem[48169] = 2030\nmask = 00XX000100000X0001110010X11001000101\nmem[25353] = 78809\nmem[10239] = 276125\nmem[11223] = 13545\nmem[57147] = 1433\nmask = 001X010010011010X110101X11011X001010\nmem[49575] = 152755\nmem[26848] = 63016\nmem[30880] = 1725364\nmask = 011101X10XX01X0010X0XX01110000X11100\nmem[41287] = 414662075\nmem[28188] = 3477472\nmem[36592] = 7996235\nmem[49314] = 9140605\nmem[47488] = 7769\nmask = 01110X01X111010001X100101X0X10011100\nmem[52240] = 192560204\nmem[65031] = 701796\nmem[14783] = 8161\nmask = 0111010X011X1X001XX01001X1X00X0100X0\nmem[49691] = 10412561\nmem[915] = 134624030\nmem[8606] = 736\nmem[58998] = 1516\nmem[61106] = 641573\nmask = 01X1X1X111101011011X10100010000X01X1\nmem[21630] = 19000288\nmem[60944] = 213511689\nmem[46464] = 59732\nmem[32695] = 533\nmem[27385] = 25467104\nmem[62812] = 304550708\nmask = 00110X010X1110X0X1111X00111X0001X100\nmem[57194] = 24040\nmem[20550] = 46674\nmem[52168] = 23339025\nmem[35512] = 68290970\nmask = X111011X1010101011101X10000111000X11\nmem[12546] = 102729072\nmem[7304] = 110383\nmem[59523] = 888\nmem[21630] = 71914682\nmem[19910] = 5863216\nmask = 11X0010X1X101110111X01X0000111111001\nmem[4963] = 173772407\nmem[33919] = 81154860\nmem[7928] = 4393\nmem[59961] = 4977369\nmem[22493] = 377398427\nmask = 00X0010X10011X10X1X00001X10001101010\nmem[17461] = 120207\nmem[7618] = 54871412\nmem[3960] = 25088\nmem[4185] = 15852\nmask = X0X00X00011010000100001X001110010101\nmem[17246] = 10825\nmem[61485] = 1220754\nmem[37937] = 63325\nmem[13776] = 44579590\nmem[26933] = 467904\nmask = 01X10X011XX101001001X101X11110100111\nmem[51192] = 227116205\nmem[8996] = 589323\nmem[40539] = 2892\nmem[18976] = 329283\nmem[2942] = 13450\nmem[59167] = 436935\nmask = 0X11010101111000X11010XX011X1X010001\nmem[31621] = 4166\nmem[38132] = 66956\nmem[4178] = 32378\nmem[59276] = 448\nmem[57505] = 202001993\nmem[8239] = 69424389\nmask = 0010X10001101000X1X00000001000001X00\nmem[32804] = 120932430\nmem[60447] = 305\nmem[62385] = 5277\nmem[54940] = 11685990\nmem[48466] = 1956474\nmem[38326] = 14070\nmask = 1111010111X0101X11101000X0011X0000X0\nmem[9957] = 794590\nmem[43663] = 63819868\nmem[59142] = 8388971\nmem[41877] = 413661620\nmask = X011X10X111XXX001111010101XXX0011001\nmem[47] = 18787684\nmem[17443] = 648\nmem[4356] = 656\nmem[65126] = 3205798\nmem[38437] = 1762627\nmem[43834] = 581\nmask = 01110110X10010001100X01010101X0X1001\nmem[38345] = 934\nmem[58528] = 6065840\nmem[33800] = 20485490\nmem[48232] = 16644491\nmask = 001X000X00X001000X11XX10101010X111X0\nmem[22284] = 259142\nmem[28461] = 974\nmem[62938] = 449\nmem[40024] = 598\nmem[22913] = 650\nmem[19996] = 5093\nmask = 0X100X01X1X010XX01X00000010000100001\nmem[29565] = 3981254\nmem[55283] = 82851782\nmem[36252] = 55697822\nmask = 111101X11X101XX01110XX1010X101XX0101\nmem[56566] = 29617152\nmem[10468] = 2660883\nmem[28055] = 1499\nmem[35218] = 185790979\nmem[22128] = 846102\nmem[30117] = 560842\nmem[58299] = 1012387364\nmask = 111X010110X0X010111001X0X00X11011111\nmem[48718] = 3937\nmem[26689] = 501780\nmem[30553] = 657149\nmask = 001001XXX0X110X001100001X1X1111X0010\nmem[1480] = 42916\nmem[27601] = 42064531\nmem[35919] = 6321\nmem[57905] = 2686675\nmask = X1110X1X110010X01X101111010110X0X101\nmem[40159] = 28365\nmem[50570] = 95027\nmem[65427] = 4531\nmem[7468] = 41\nmem[49166] = 3783\nmem[59623] = 258901051\nmask = 1111011X1100100X111X0X0X11011X1001X0\nmem[58839] = 103790\nmem[44410] = 660\nmask = 01X1010X0111100X0110001101XX00X1110X\nmem[58804] = 116385\nmem[57709] = 27103\nmem[62492] = 11102\nmem[62031] = 24113\nmem[23580] = 101015510\nmask = 0X110100011110010110111100X11X110X01\nmem[45855] = 345\nmem[33796] = 2069488\nmem[61694] = 409\nmem[42908] = 529\nmem[54356] = 1540\nmem[2931] = 6893\nmem[13629] = 161\nmask = 011X010111111000X11001010XX0100110X1\nmem[48232] = 2748\nmem[46818] = 1360569\nmem[37978] = 59020939\nmem[3596] = 13931\nmask = 011X0101011110X00110110010X11100XX00\nmem[8686] = 3043212\nmem[99] = 1008741\nmem[19665] = 4017\nmem[57438] = 94605425\nmem[55264] = 1505951\nmask = 0010XX11X00X1X000110X0X101X1010000X1\nmem[38124] = 952\nmem[24080] = 109634238\nmem[9221] = 3755102\nmem[6097] = 7229649\nmem[44702] = 6963\nmem[4664] = 75052121\nmem[54208] = 50749\nmask = 001X000100X001000111X0XX101110X0X00X\nmem[29210] = 30278\nmem[34588] = 8139931\nmem[4262] = 170\nmem[30822] = 116256036\nmem[35104] = 2105\nmem[53802] = 202809516\nmem[34175] = 11635\nmask = 10110111101X10X01110X0101X1101X1000X\nmem[31068] = 8529566\nmem[55554] = 1887440\nmem[43973] = 711\nmem[30117] = 89\nmem[32710] = 6073240\nmask = X11001011100110101X10X0X0X0111010000\nmem[33533] = 67852\nmem[41455] = 2159161\nmem[61283] = 7275538\nmem[26353] = 49998\nmem[37978] = 396\nmem[56038] = 27870483\nmem[48893] = 2558\nmask = 00110X0X10XX1001X101001001010XX10001\nmem[15308] = 3190807\nmem[29791] = 1689321\nmem[23706] = 28489987\nmem[52184] = 13941172\nmask = 0111010111X010X1011011X01110X0X10111\nmem[46818] = 3960\nmem[896] = 8079\nmem[51892] = 10813637\nmem[40539] = 50432\nmem[39200] = 405093301\nmem[59082] = 24044094\nmask = 0X11X00100110X0001111000X10000X01000\nmem[18818] = 115548503\nmem[3709] = 408\nmem[28387] = 1517\nmem[18617] = 82658\nmask = 00100X0001X0100001X010100011X0XX0001\nmem[16645] = 2580\nmem[55034] = 1395\nmem[24779] = 57573\nmask = 010100X11111000010XXXX0X0X1XX0110010\nmem[51498] = 514\nmem[56424] = 27530520\nmem[5657] = 5653\nmem[60013] = 1759\nmem[39685] = 48186\nmask = X0100X0111101XX1111001X101110XXX11X0\nmem[15867] = 15856896\nmem[16645] = 22385\nmem[11204] = 56370\nmask = 0X1X0101X0101000111011110X111010X001\nmem[30186] = 149471\nmem[55297] = 6189241\nmem[56429] = 7551\nmem[35853] = 1943\nmem[50590] = 3973692\nmask = 0111010X011X10XXX100011XX11101X0X011\nmem[41687] = 10091\nmem[27747] = 12073\nmem[41632] = 117\nmem[53319] = 6596158\nmask = 101001011110X1011110001X11111XX01001\nmem[8898] = 350890\nmem[61425] = 11351\nmem[30900] = 62547453\nmem[32345] = 4152907\nmem[53681] = 15100\nmask = 0X10010X1X0110X0011001101001X010XX11\nmem[46274] = 266245793\nmem[51690] = 6210475\nmem[31828] = 50916780\nmem[40159] = 1305075\nmask = X1110X0001XX11001100111111X0100X00X0\nmem[13308] = 2061153\nmem[21134] = 100\nmem[22800] = 43346237\nmem[63215] = 1528\nmem[3115] = 159268\nmem[51760] = 3390\nmask = 011X0101011100000110X1X1X1101X1100X1\nmem[3709] = 1727\nmem[30692] = 4483\nmem[4143] = 22098690\nmem[8978] = 536\nmask = 1X1X01011X10X101X110000X11111010X100\nmem[1983] = 1355679\nmem[18831] = 4847364\nmem[26861] = 6413\nmask = 111XXX10X11010001X10X10X0111100X1X00\nmem[1940] = 622180477\nmem[33455] = 6793\nmem[54070] = 74933929\nmem[1936] = 6134620\nmask = 1X1X010X111011XX1110010100X1X0X01X01\nmem[4347] = 29272422\nmem[26401] = 180116335\nmem[53382] = 684\nmem[29142] = 91826\nmem[30] = 11042\nmem[30490] = 92215\nmask = 11X101111110110011100111X1X11X1001X1\nmem[35424] = 3594\nmem[54399] = 228781216\nmem[33796] = 149514889\nmem[29544] = 697176893\nmem[38146] = 169131\nmem[35144] = 33941748\nmem[39849] = 293\nmask = XX1101XX1X101000111011011110X0100101\nmem[11432] = 76469\nmem[19223] = 5525\nmem[45424] = 201\nmem[3709] = 5622752\nmem[64781] = 8199\nmask = 011001011X1110011111X0X00X1000000101\nmem[54292] = 949\nmem[26880] = 10542\nmem[62098] = 860\nmask = 111X010X111011111X10001X01X100X11001\nmem[22272] = 951175\nmem[20506] = 1133335\nmem[57502] = 177607519\nmask = 01100X0101X1110X01X01101X11XX1X111X0\nmem[30880] = 1070\nmem[25208] = 25110\nmem[29830] = 43106\nmem[44754] = 7361\nmask = 0X1X0101111X100XX11X0100010100X00101\nmem[57107] = 9040\nmem[19968] = 326431400\nmem[30553] = 2988\nmem[1096] = 54078\nmem[5657] = 822343\nmem[24080] = 241413\nmem[54292] = 14129212\nmask = 0X110X010X1XXX00011111X001X010X10000\nmem[43660] = 37456160\nmem[51849] = 1253870\nmask = 111001X0X1X01000111000X00XX00010X000\nmem[11663] = 66023717\nmem[26130] = 503044\nmem[44779] = 44698095\nmem[12702] = 53757\nmem[22414] = 1795507\nmask = 0111X1010111X1X0X1100X010001X0011010\nmem[39200] = 4090252\nmem[31845] = 2782402\nmem[22284] = 429\nmem[29279] = 16254306\nmask = 01010101111X0X000X1011X11XX11X1XX111\nmem[50705] = 1963\nmem[40289] = 71985\nmem[43716] = 411505145\nmem[3338] = 661\nmem[2430] = 2635"
}
