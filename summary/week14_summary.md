# Week 14 트리
## 트리

### 트리란?

![tree_jpeg](https://github.com/younggeun0/DataStructureStudy/blob/master/week14/younggeun0/img/tree.jpeg?raw=true)

* 트리는 데이터 사이의 계층 관계를 나타내는 자료구조
* **노드(node)와 가지(edge)**
  * 각각의 노드는 가지를 통해 다른 노드와 연결되어 있음
* **루트(root)** 는 트리의 가장 윗부분에 위치
  * 하나의 트리에는 하나의 루트만 존재
* **리프(leaf)** 는 트리의 가장 아랫부분
  * 가장 아래는 물리적인 위치가 아님
  * leaf == terminal node == external node
* **안쪽 노드** 는 루트를 포함하여 리프를 제외한 노드를 안쪽 노드라고 함
  * == non-terminal node
* **자식(child)** 는 어떤 노드로부터 가지에 연결된 아래쪽 노드
  * 리프는 자식을 가질 수 없음
* **부모(parent)** 는 어떤 노드에서 가지로 연결된 위쪽 노드
* **형제(sibling)** 은 같은 부모를 가지는 노드
* **조상(ancestor)** 은 어떤 노드에서 가지로 연결된 위쪽 노드 모두
* **자손(descendant)** 는 어떤 노드에서 가지로 연결된 아래쪽 노드 모두
* **레벨(level)** 은 루트로부터 얼마나 떨어져 있는지에 대한 값
  * root의 레벨은 0, 루트로부터 가지가 하나씩 아래로 뻗어나갈 때마다 레벨이 1씩 증가
* **차수(degree)** 는 노드가 갖는 자식의 수
  * 모든 노드의 차수가 n이하인 트리를 n진 트리라고 함* **높이(height)** 는 루트로부터 가장 멀리 떨어진 리프까지의 거리
* **서브 트리(subtree)** 는 트리 안에서 다시 어떤 노드를 루트로 정하고 그 자손으로 이루어진 트리
* **널 트리(null tree)** 는 가지가 없는 트리
* **순서 트리(ordered tree)와 무순서 트리(unordered tree)** 는 형제 노드의 순서가 있는지 없는지에 따라 두 종류로 분류한 것

### 순서 트리 탐색

![bfs-dfs_png](https://github.com/younggeun0/DataStructureStudy/blob/master/week14/younggeun0/img/bfs-dfs.png?raw=true)

* 순서 트리의 노드를 스캔하는 방법은 두가지
  * **너비 우선 탐색(Breadth-First Search, BFS)**
    * 낮은 레벨에서 시작해 왼쪽에서 오른쪽 방향으로 검색하고 한 레벨에서의 검색이 끝나면 다음 레벨로 이동
  * **깊이 우선 탐색(Depth-Frist Search, DFS)**
    * 리프까지 내려가면서 검색하는 것을 우선순위로 하는 탐색 방법
    * 리프에 도달해 더 이상 검색을 진행할 곳이 없는 경우 부모에게 돌아감
    * 그런 다음 다시 자식 노드로 내려감
* 노드를 탐색할 때 '언제 노드를 방문할지'에 따라 세 종류로 구분
  * **전위 순회(Preorder)**
    * 노드 방문 -> 왼쪽 자식 -> 오른쪽 자식
  * **중위 순회(Inorder)**
    * 왼쪽 자식 -> 노드 방문 -> 오른쪽 자식
  * **후위 순회(Postorder)**
    * 왼쪽 자식 -> 오른쪽 자식 -> (돌아와)노드 방문
