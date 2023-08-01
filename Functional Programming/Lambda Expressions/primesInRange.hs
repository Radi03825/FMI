main :: IO()
main = do
    print (primesInRange 1 17)
    print (primesInRange 1 100)



primesInRange :: Int -> Int -> [Int]
primesInRange start end = filter (\ x -> isPrime x) [min start end .. max start end]

isPrime :: Int -> Bool
isPrime n = (n > 1) && null (filter (\ x -> mod n x == 0) [2 .. div n 2])
