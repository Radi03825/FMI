main :: IO()
main = do
    print (reverseNumber 1)
    print (reverseNumber 12)
    print (reverseNumber 123)
    print (reverseNumberWithHelper 1234)
    print (reverseNumberWithHelper 12345)



reverseNumber :: Integer -> Integer
reverseNumber n
    | n == 0 = 0
    | otherwise = n `mod` 10 * power 10 ((countDigits n) - 1) + reverseNumber (n `div` 10)

countDigits :: Integer -> Integer
countDigits n 
    | n == 0 = 0
    | otherwise = 1 + countDigits (n `div` 10)

power :: Integer -> Integer -> Integer
power x y
    | y == 0 = 1
    | otherwise = x * power x (y - 1)


reverseNumberWithHelper :: Integer -> Integer
reverseNumberWithHelper n = helper n 0
    where
        helper :: Integer -> Integer -> Integer
        helper 0 result = result
        helper leftover result = helper (leftover `div` 10) (result * 10 + leftover `mod` 10)