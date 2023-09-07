import Data.Char
import Data.List

main :: IO()
main = do
    print (simplify "1+2+x" == "x+3")
    print (simplify "x+2+x-2" == "2x")
    print (simplify "x+2-(x-2)" == "4")
    print (simplify "y+2+x-2" == "x+y")
    print (simplify "1+2+x+y+x+z+5-x-x-x+y" == "-x+2y+z+8")
    print (simplify "1+2+x+y+x-(x-x-x)+z+y-9" == "3x+2y+z-6")
    print (simplify "1+2-(3-(3-2))-9" == "-8")
    print (simplify "x-7+z-x+7-z" == "")
    print (simplify "(((1+x-(y+z)-x)+2)-2)" == "-y-z+1")
    print (simplify "3+7+x-y+z+x-3" == "2x-y+z+7")



simplify :: String -> String
simplify expr = trim (concatMap termToStr terms ++ showNum num)
  where
    tokens = (tokenize . expand . normalize) expr

    vars = (sort . nub) [v | (_, v) <- tokens, v >= 'a', v <= 'z' ]

    terms = [(sum [if (s == '+') then 1 else -1 | (s, v) <- tokens, v == var], var) | var <- vars]

    num = sum [(if (s == '+') then 1 else -1) * digitToInt d | (s, d) <- tokens, d >= '0', d <= '9']

    showNum 0 = ""
    showNum n = if (n > 0) then '+':show n else show n

    termToStr ( 0, v) = ""
    termToStr (-1, v) = ['-', v]
    termToStr ( 1, v) = ['+', v]
    termToStr ( d, v) = showNum d ++ [v]

    trim ('+':cs) = cs
    trim cs = cs

tokenize :: String -> [(Char, Char)]
tokenize "" = []
tokenize (c1:c2:cs) = (c1, c2):tokenize cs

normalize :: String -> String
normalize "" = ""
normalize ('+':c:cs) = '+':c:normalize cs
normalize ('-':c:cs) = '-':c:normalize cs
normalize ('(':'+':cs) = '+':'(':normalize cs
normalize (')':cs) = ')':normalize cs
normalize (c:cs) = '+':c:normalize cs

expand :: String -> String
expand cs = if (maxD == 0) then cs else expand (process cs ds)
  where    
    enum ')' (counter, res) = (counter + 1, counter + 1:res)
    enum '(' (counter, res) = (counter - 1, counter:res)
    enum _   (counter, res) = (counter,     counter:res)

    ds = (snd (foldr enum (0, []) cs))

    maxD = maximum ds
    
    process "" _ = ""
    process (c1:c2:cs) (d1:d2:ds) = if (d1 == maxD - 1 && d2 == maxD) then alter cs else c1:process (c2:cs) (d2:ds)
      where
        alter "" = ""
        alter (')':cs) = cs
        alter ('+':c:cs) = (if (c1 == '+') then '+' else '-'):c:alter cs
        alter ('-':c:cs) = (if (c1 == '+') then '-' else '+'):c:alter cs
