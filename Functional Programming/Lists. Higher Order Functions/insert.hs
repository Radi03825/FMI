main :: IO()
main = do
    print (insert 5 [1,2,3,4,6,7,8,9,10])
    print (insertionSort [4,6,2,1,5,3,8,7,10,9])



insert :: Int -> [Int] -> [Int]
insert a [] = [a]
insert a bs@(b:tb) = if (a < b) then a:bs else b:insert a tb

-- insert + foldr
insertionSort :: [Int] -> [Int]
insertionSort = foldr insert []
