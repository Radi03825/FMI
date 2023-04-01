main :: IO()
main = do
    print (removeDigit 656 5)
    print (removeDigit 656 1)
    print (removeDigit 600 0)


removeDigit :: Integer -> Integer -> Integer
removeDigit num digit
    | num == 0 = 0
    | num `mod` 10 == digit = removeDigit (num `div` 10) digit
    | otherwise = (removeDigit (num `div` 10) digit) * 10 + (num `mod` 10)