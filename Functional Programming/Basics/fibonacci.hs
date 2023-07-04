main :: IO()
main = do
    print (fibonacci 1)
    print (fibonacci 5)
    print (fibonacci 7)



fibonacci :: Integer -> Integer
fibonacci x
    | x == 0 = 0
    | x == 1 || x == 2 = 1
    | otherwise = fibonacci (x - 1) + fibonacci (x - 2)
