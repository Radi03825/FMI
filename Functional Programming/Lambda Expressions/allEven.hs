main :: IO()
main = do
    print (allEven [1..17])
    print (lastEvenElement [1..17])
    print (firstEvenElement [1..17])


allEven :: [Int] -> [Int]
allEven = foldr (\ el res -> if (even el) then el:res else res) []
-- allEven xs = foldr (\ el res -> if (even el) then el:res else res) [] xs

lastEvenElement :: [Int] -> Int
lastEvenElement = foldl (\ el res -> if (even res) then res else el) (error "No even element found")
-- lastEvenElement xs = foldl (\ el res -> if even res then res else el) (error "No even element found") xs

firstEvenElement :: [Int] -> Int
firstEvenElement = foldr (\ el res -> if (even el) then el else res) (error "No even element found")
-- firstEvenElement xs = foldr (\ el res -> if (even el) then el else res) (error "No even element found") xs