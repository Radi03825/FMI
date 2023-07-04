main :: IO()
main = do
    print (sumDigits 123456)



sumDigits :: Integer -> Integer
sumDigits n 
    | n < 10 = n
    | otherwise = (n `mod` 10) + sumDigits (n `div` 10)