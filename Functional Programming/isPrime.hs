main :: IO()
main = do
    print (isPrime 3)
    print (isPrime 6)
    print (isPrime 17)



isPrime :: Integer -> Bool
isPrime 1 = False
isPrime n = helper (n - 1)
    where 
        helper :: Integer -> Bool
        helper current
         | current == 1 = True
         | (n `mod` current) == 0 = False
         | otherwise = helper (current - 1)