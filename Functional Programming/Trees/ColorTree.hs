main :: IO()
main = do
    print (maxDepthBlueNode colorTree)
    print (maxDepthNode colorTree Blue)
    print (containsColor colorTree Blue)


data Color = Red | Green | Blue | Yellow | Black
    deriving (Read, Show, Eq)

data BTree a = Empty | Node a (BTree a) (BTree a)

colorTree :: BTree Color                                            --            Blue
colorTree = Node Blue (Node Red (Node Green Empty Empty) Empty)     --           /    \
                      (Node Red (Node Blue (Node Green Empty Empty) --        Red      Red
                                           (Node Red Empty Empty))  --        /        /  
                                Empty)                              --     Green     Blue  
                                                                    --               /   \
                                                                    --            Green  Red



maxDepthBlueNode :: BTree Color -> Int
maxDepthBlueNode bt = helper 0 bt
 where
    helper _ Empty = -1
    helper k (Node Blue lt rt) = max k (max (helper (k + 1) lt) (helper (k + 1) rt))
    helper k (Node _ lt rt)    = max (helper (k + 1) lt) (helper (k + 1) rt)

maxDepthNode :: BTree Color -> Color -> Int
maxDepthNode bt c = helper 0 bt
 where
    helper _ Empty = -1
    helper k (Node nc lt rt) 
     | nc == c   = max k (max (helper (k + 1) lt) (helper (k + 1) rt))
     | otherwise = max (helper (k + 1) lt) (helper (k + 1) rt)

containsColor :: BTree Color -> Color -> Bool
containsColor Empty _ = False
containsColor (Node cl lt rt) c
 | cl == c   = True
 | otherwise = containsColor lt c || containsColor rt c
