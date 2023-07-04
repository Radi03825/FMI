main :: IO()
main = do
    print (isPrime 3)
    print (isPrime 6)
    print (isPrime 17)

    print (isPrimeLC 3)
    print (isPrimeLC 6)
    print (isPrimeLC 17)



isPrime :: Integer -> Bool
isPrime 1 = False
isPrime n = helper (n - 1)
    where 
        helper :: Integer -> Bool
        helper current
         | current == 1 = True
         | (n `mod` current) == 0 = False
         | otherwise = helper (current - 1)

-- With List Comprehension
isPrimeLC :: Integer -> Bool
isPrimeLC n = [x | x <- [2..(n - 1)], n `rem` x == 0] == []