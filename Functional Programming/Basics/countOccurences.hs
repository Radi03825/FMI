main :: IO()
main = do
    print (countOccurences 121 1)
    print (countOccurences 123 3)
    print (countOccurences 222 1)



countOccurences :: Integer -> Integer -> Integer
countOccurences num digit = helper num digit 0
 where
    helper :: Integer -> Integer -> Integer -> Integer
    helper num digit count 
     | num == 0 = count
     | num `mod` 10 == digit = helper (num `div` 10) digit (count + 1)
     | otherwise = helper (num `div` 10) digit count 