main :: IO()
main = do
  print (removeAll 2 [1,2,3,2,3,2] == [1,3,3])
  print (removeAll' 2 [1,2,3,2,3,2] == [1,3,3])



removeAll :: (Eq a) => a -> [a] -> [a]
removeAll _ [] = []
removeAll n (x:xs) = 
    if (n == x) then removeAll n xs
    else x:removeAll n xs

removeAll' :: (Eq a) => a -> [a] -> [a]
removeAll' n xs = [x | x <- xs, x /= n]
