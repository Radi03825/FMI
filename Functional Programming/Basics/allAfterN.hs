main :: IO()
main = do
    print (take 5 (allAfterN 3))
    print (take 3 (allAfterN 99))
    print (take 15 (allAfterN 27))



allAfterN :: Int -> [Int]
allAfterN n = (n + 1):allAfterN (n + 1)