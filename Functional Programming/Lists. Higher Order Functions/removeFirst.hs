main :: IO()
main = do
    print (removeFirst 2 [1,2,3,2,3,2] == [1,3,2,3,2])
    print (removeFirst 3 [1,2,3,2,3,2] == [1,2,2,3,2])



removeFirst :: Eq a => a -> [a] -> [a]
removeFirst _ [] = []
removeFirst n (x:xs) = 
    if (n == x) then xs
    else x:removeFirst n xs
