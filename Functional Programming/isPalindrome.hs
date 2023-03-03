main :: IO()
main = do
    print (isPalindrome 123)
    print (isPalindrome 1221)
    print (isPalindrome 12321)



isPalindrome :: Integer -> Bool
isPalindrome n = n == reverseNumber n

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